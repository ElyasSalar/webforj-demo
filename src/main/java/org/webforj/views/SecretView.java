package org.webforj.views;

import com.webforj.annotation.InlineStyleSheet;
import com.webforj.component.Composite;
import com.webforj.component.Expanse;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.html.elements.*;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.*;
import com.webforj.router.Router;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;
import com.webforj.router.event.WillEnterEvent;
import com.webforj.router.history.ParametersBag;
import com.webforj.router.observer.WillEnterObserver;

/**
 * A protected secret page that requires WebAuthn authentication to access.
 * Implements WillEnterObserver to guard the route.
 */
@Route("secret")
@FrameTitle("Secret Page")
@InlineStyleSheet(/* css */ """
  .secret-page {
    max-width: 700px;
    margin: 0 auto;
    padding: var(--dwc-space-xl);
  }
  .secret-card {
    background: var(--dwc-surface-2);
    border-radius: var(--dwc-border-radius-m);
    padding: var(--dwc-space-xl);
    box-shadow: var(--dwc-shadow-m);
    text-align: center;
  }
  .secret-card h1 {
    color: var(--dwc-color-success);
    margin-bottom: var(--dwc-space-m);
  }
  .secret-content {
    background: var(--dwc-surface-3);
    border-radius: var(--dwc-border-radius-s);
    padding: var(--dwc-space-l);
    margin: var(--dwc-space-l) 0;
    text-align: left;
  }
  .secret-content code {
    background: var(--dwc-surface-1);
    padding: 2px 6px;
    border-radius: var(--dwc-border-radius-xs);
    font-size: 0.9em;
  }
""")
public class SecretView extends Composite<FlexLayout> implements WillEnterObserver {

  @Override
  public void onWillEnter(WillEnterEvent event, ParametersBag parameters) {
    if (!AuthState.isAuthenticated()) {
      event.veto(true);
      event.getRouter().navigate(HomeView.class);
    } else {
      event.accept();
    }
  }

  public SecretView() {
    FlexLayout self = getBoundComponent();
    self.setDirection(FlexDirection.COLUMN)
        .setJustifyContent(FlexJustifyContent.CENTER)
        .addClassName("secret-page");

    Div card = new Div();
    card.addClassName("secret-card");

    H1 title = new H1("You're In!");
    Paragraph greeting = new Paragraph(
        "Welcome, " + AuthState.getAuthenticatedUser()
        + ". You authenticated successfully using WebAuthn.");

    Div content = new Div();
    content.addClassName("secret-content");

    H3 contentTitle = new H3("How it works");

    Paragraph step1 = new Paragraph(
        "1. The browser called navigator.credentials.get() with the options "
        + "configured on the home page.");
    Paragraph step2 = new Paragraph(
        "2. Your authenticator (biometrics, security key, etc.) signed a challenge "
        + "to prove possession of the private key.");
    Paragraph step3 = new Paragraph(
        "3. The server validated the response: credential ID, origin, challenge, "
        + "and signature were all verified.");
    Paragraph step4 = new Paragraph(
        "4. This route is protected by a WillEnterObserver that checks "
        + "authentication state before rendering.");

    content.add(contentTitle, step1, step2, step3, step4);

    Button logoutBtn = new Button("Logout & Return Home", ButtonTheme.OUTLINED_DANGER);
    logoutBtn.setExpanse(Expanse.LARGE);
    logoutBtn.setPrefixComponent(TablerIcon.create("logout"));
    logoutBtn.onClick(e -> {
      AuthState.logout();
      Router.getCurrent().navigate(HomeView.class);
    });

    card.add(title, greeting, content, logoutBtn);
    self.add(card);
  }
}
