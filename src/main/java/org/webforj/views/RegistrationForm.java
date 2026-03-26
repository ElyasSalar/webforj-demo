package org.webforj.views;

import static org.webforj.views.DemoComponents.*;

import com.webforj.addons.services.webauthn.RelyingParty;
import com.webforj.addons.services.webauthn.data.*;
import com.webforj.component.Composite;
import com.webforj.component.Expanse;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.field.TextField;
import com.webforj.component.html.elements.Div;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.list.ChoiceBox;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Self-contained registration form. Builds WebAuthn creation options from the UI controls, runs
 * the registration ceremony, and stores the resulting credential.
 */
class RegistrationForm extends Composite<FlexLayout> {

  private final RelyingParty relyingParty;
  private final Consumer<String> logger;

  private final TextField usernameField = new TextField();
  private final TextField displayNameField = new TextField();

  private final ChoiceBox attachmentChoice = createChoice("Authenticator Attachment", 0,
      Map.entry("any", "Any (default)"),
      Map.entry("platform", "Platform (biometrics)"),
      Map.entry("cross-platform", "Cross-Platform (security key)"));

  private final ChoiceBox residentKeyChoice = createChoice("Resident Key (Passkey)", 1,
      Map.entry("discouraged", "Discouraged"),
      Map.entry("preferred", "Preferred"),
      Map.entry("required", "Required"));

  private final ChoiceBox userVerificationChoice = createChoice("User Verification", 1,
      Map.entry("discouraged", "Discouraged"),
      Map.entry("preferred", "Preferred (default)"),
      Map.entry("required", "Required"));

  private final ChoiceBox attestationChoice = createChoice("Attestation Preference", 0,
      Map.entry("none", "None"),
      Map.entry("indirect", "Indirect"),
      Map.entry("direct", "Direct"),
      Map.entry("enterprise", "Enterprise"));

  RegistrationForm(RelyingParty relyingParty, Consumer<String> logger) {
    this.relyingParty = relyingParty;
    this.logger = logger;

    FlexLayout self = getBoundComponent();
    self.setDirection(FlexDirection.COLUMN)
        .setSpacing("var(--dwc-space-m)")
        .setPadding("var(--dwc-space-l)");

    usernameField.setLabel("Username")
        .setPlaceholder("e.g. john.doe")
        .setRequired(true)
        .setWidth("100%");

    displayNameField.setLabel("Display Name")
        .setPlaceholder("e.g. John Doe")
        .setRequired(true)
        .setWidth("100%");

    Div optionsGrid = new Div();
    optionsGrid.addClassName("options-grid");
    optionsGrid.add(attachmentChoice, residentKeyChoice,
        userVerificationChoice, attestationChoice);

    Button registerBtn = new Button("Register Passkey", ButtonTheme.PRIMARY);
    registerBtn.setExpanse(Expanse.LARGE);
    registerBtn.setPrefixComponent(TablerIcon.create("fingerprint"));
    registerBtn.onClick(e -> performRegistration());

    self.add(
        card("User Information", usernameField, displayNameField),
        card("Authenticator Options", optionsGrid),
        registerBtn);
  }

  private void performRegistration() {
    String username = usernameField.getValue();
    String displayName = displayNameField.getValue();

    if (username == null || username.isBlank()) {
      logger.accept("ERROR: Username is required");
      return;
    }
    if (displayName == null || displayName.isBlank()) {
      displayName = username;
    }

    logger.accept("Starting registration for: " + username);

    PublicKeyCredentialCreationOptions options =
        new PublicKeyCredentialCreationOptions(new UserIdentity(username, displayName));
    options.setAuthenticatorSelection(buildAuthenticatorSelection());
    configureAttestation(options);

    logger.accept("Options configured — initiating ceremony…");

    relyingParty.register(options)
        .thenAccept(response -> onSuccess(username, response))
        .exceptionally(error -> {
          Throwable cause = error.getCause() != null ? error.getCause() : error;
          logger.accept("Registration failed: " + cause.getMessage());
          return null;
        });
  }

  private void onSuccess(String username, RegistrationResponse response) {
    logger.accept("Registration successful!");
    logger.accept("  Credential ID: " + truncate(response.getId(), 20));
    logger.accept("  Type: " + response.getType());
    if (response.getAuthenticatorAttachment() != null) {
      logger.accept("  Attachment: " + response.getAuthenticatorAttachment());
    }
    logger.accept("  Algorithm: " + response.getResponse().getPublicKeyAlgorithm());
  }

  private AuthenticatorSelectionCriteria buildAuthenticatorSelection() {
    AuthenticatorAttachment attachment = switch (selectedKey(attachmentChoice)) {
      case "platform" -> AuthenticatorAttachment.PLATFORM;
      case "cross-platform" -> AuthenticatorAttachment.CROSS_PLATFORM;
      default -> null;
    };

    return new AuthenticatorSelectionCriteria(
        attachment,
        ResidentKeyRequirement.valueOf(selectedKey(residentKeyChoice).toUpperCase()),
        UserVerificationRequirement.valueOf(selectedKey(userVerificationChoice).toUpperCase()));
  }

  private void configureAttestation(PublicKeyCredentialCreationOptions options) {
    String value = selectedKey(attestationChoice);
    if (!"none".equals(value)) {
      options.setAttestation(AttestationConveyancePreference.valueOf(value.toUpperCase()));
    }
  }
}
