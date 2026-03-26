package org.webforj.views;

import com.webforj.addons.services.webauthn.RelyingParty;
import com.webforj.addons.services.webauthn.data.RelyingPartyIdentity;
import com.webforj.annotation.InlineStyleSheet;
import com.webforj.component.Composite;
import com.webforj.component.Theme;
import com.webforj.component.alert.Alert;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H1;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.tabbedpane.Tab;
import com.webforj.component.tabbedpane.TabbedPane;
import com.webforj.router.Router;
import com.webforj.router.annotation.FrameTitle;
import com.webforj.router.annotation.Route;

import java.time.LocalTime;

/**
 * Main view for the WebAuthn passwordless authentication demo. Assembles the header, browser
 * support alert, tabbed forms (registration, authentication, options reference), and activity log.
 *
 * <p>Each tab is a self-contained {@link Composite} component; this view only wires them together
 * and owns the shared activity log.
 */
@Route("/")
@FrameTitle("WebAuthn Demo")
@InlineStyleSheet("""
    .webauthn-demo {
      max-width: 900px;
      margin: 0 auto;
      padding: var(--dwc-space-l);
    }

    .card {
      background: var(--dwc-surface-2);
      border-radius: var(--dwc-border-radius-m);
      padding: var(--dwc-space-l);
      margin-bottom: var(--dwc-space-l);
      box-shadow: var(--dwc-shadow-s);
    }

    .card__title {
      color: var(--dwc-color-primary);
      border-bottom: 2px solid var(--dwc-color-primary);
      padding-bottom: var(--dwc-space-xs);
      margin-bottom: var(--dwc-space-m);
    }

    .header {
      text-align: center;
      margin-bottom: var(--dwc-space-xl);
    }

    .header h1 {
      color: var(--dwc-color-primary);
      margin-bottom: var(--dwc-space-s);
    }

    .options-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: var(--dwc-space-m);
    }

    .activity-log {
      background: var(--dwc-surface-3);
      border-radius: var(--dwc-border-radius-s);
      padding: var(--dwc-space-m);
      font-family: monospace;
      font-size: 0.85em;
      max-height: 200px;
      overflow-y: auto;
    }
    """)
public class HomeView extends Composite<FlexLayout> {

  private static final String RP_NAME = "WebAuthn Demo";
  private static final String RP_ID = "localhost";
  private static final String RP_ORIGIN = "http://localhost:8080";

  private final FlexLayout self = getBoundComponent();
  private final RelyingParty relyingParty =
      new RelyingParty(new RelyingPartyIdentity(RP_NAME, RP_ID), RP_ORIGIN);
  private final Div logPanel = new Div();

  public HomeView() {
    self.setDirection(FlexDirection.COLUMN).addClassName("webauthn-demo");
    self.add(createHeader(), createBrowserSupportAlert(), createTabs(), createActivityLog());
    detectPlatformAuthenticator();
  }

  private static Div createHeader() {
    Div header = new Div();
    header.addClassName("header");
    header.add(
        new H1("WebAuthn Passwordless Demo"),
        new Paragraph("Register a passkey, then authenticate to access the secret page."));
    return header;
  }

  private static Alert createBrowserSupportAlert() {
    Alert alert = new Alert();
    alert.setMaxWidth("100%");

    if (RelyingParty.isWebAuthnSupported()) {
      alert.setTheme(Theme.SUCCESS);
      alert.add(new Paragraph("WebAuthn is supported in this browser"));
    } else {
      alert.setTheme(Theme.DANGER);
      alert.add(new Paragraph("WebAuthn is NOT supported in this browser"));
    }

    return alert;
  }

  private TabbedPane createTabs() {
    TabbedPane tabs = new TabbedPane();
    tabs.addTab(new Tab("Register", TablerIcon.create("user-plus")),
        new RegistrationForm(relyingParty, this::log));
    tabs.addTab(new Tab("Authenticate", TablerIcon.create("login")),
        new AuthenticationForm(relyingParty, this::log, this::onAuthenticated));
    tabs.addTab(new Tab("Options Reference", TablerIcon.create("settings")),
        new OptionsReferencePanel());
    return tabs;
  }

  private Div createActivityLog() {
    logPanel.addClassName("activity-log");
    log("WebAuthn Demo initialized");
    log("RP ID: " + RP_ID);

    Button clearBtn = new Button("Clear Log", ButtonTheme.OUTLINED_DEFAULT);
    clearBtn.onClick(e -> {
      logPanel.removeAll();
      log("Log cleared");
    });

    return DemoComponents.card("Activity Log", logPanel, clearBtn);
  }

  private void detectPlatformAuthenticator() {
    if (!RelyingParty.isWebAuthnSupported()) {
      return;
    }

    Div detector = new Div();
    self.add(detector);
    RelyingParty.isPlatformAuthenticatorAvailable(detector)
        .thenAccept(available -> log(available
            ? "Platform authenticator (biometrics) available"
            : "No platform authenticator — use a security key"));
  }

  private void onAuthenticated(String userHandle) {
    AuthState.login(userHandle != null ? userHandle : "authenticated-user");
    log("Navigating to secret page…");
    Router.getCurrent().navigate(SecretView.class);
  }

  private void log(String message) {
    Div entry = new Div();
    entry.setText("[" + LocalTime.now().toString().substring(0, 8) + "] " + message);
    logPanel.add(entry);
  }
}
