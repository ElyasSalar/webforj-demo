package org.webforj.views;

import static org.webforj.views.DemoComponents.*;

import com.webforj.addons.services.webauthn.RelyingParty;
import com.webforj.addons.services.webauthn.data.*;
import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;

import java.util.function.Consumer;

/**
 * Self-contained authentication form. Runs the WebAuthn authentication ceremony and delegates the
 * resulting user handle to an external callback for login and navigation.
 */
class AuthenticationForm extends Composite<FlexLayout> {

  private final RelyingParty relyingParty;
  private final Consumer<String> logger;
  private final Consumer<String> onAuthenticated;

  AuthenticationForm(RelyingParty relyingParty, Consumer<String> logger,
      Consumer<String> onAuthenticated) {
    this.relyingParty = relyingParty;
    this.logger = logger;
    this.onAuthenticated = onAuthenticated;

    FlexLayout self = getBoundComponent();
    self.setDirection(FlexDirection.COLUMN)
        .setSpacing("var(--dwc-space-m)")
        .setPadding("var(--dwc-space-l)");

    Paragraph info = new Paragraph(
        "Authenticate with a registered passkey. "
            + "On success you will be redirected to the protected secret page.");

    Button authBtn = new Button("Authenticate with Passkey", ButtonTheme.SUCCESS);
    authBtn.setPrefixComponent(TablerIcon.create("shield-check"));
    authBtn.onClick(e -> performAuthentication());

    Button cancelBtn = new Button("Cancel Ceremony", ButtonTheme.OUTLINED_DANGER);
    cancelBtn.setPrefixComponent(TablerIcon.create("x"));
    cancelBtn.onClick(e -> {
      relyingParty.cancelCeremony();
      logger.accept("WebAuthn ceremony cancelled");
    });

    FlexLayout buttons = new FlexLayout();
    buttons.setSpacing("var(--dwc-space-m)");
    buttons.add(authBtn, cancelBtn);

    self.add(card("Authentication", info, buttons));
  }

  private void performAuthentication() {
    logger.accept("Starting authentication…");

    PublicKeyCredentialGetOptions options = new PublicKeyCredentialGetOptions();

    relyingParty.authenticate(options)
        .thenAccept(this::onSuccess)
        .exceptionally(error -> {
          Throwable cause = error.getCause() != null ? error.getCause() : error;
          logger.accept("Authentication failed: " + cause.getMessage());
          return null;
        });
  }

  private void onSuccess(AuthenticationResponse response) {
    logger.accept("Authentication successful!");
    logger.accept("  Credential ID: " + truncate(response.getId(), 20));

    String userHandle = response.getResponse().getUserHandle();
    logger.accept("  User Handle: " + (userHandle != null ? userHandle : "N/A"));

    onAuthenticated.accept(userHandle);
  }
}
