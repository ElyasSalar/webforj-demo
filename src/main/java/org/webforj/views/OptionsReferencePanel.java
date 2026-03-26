package org.webforj.views;

import static org.webforj.views.DemoComponents.*;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;

/**
 * Read-only reference panel explaining each WebAuthn option and when to use each value.
 */
class OptionsReferencePanel extends Composite<FlexLayout> {

  OptionsReferencePanel() {
    FlexLayout self = getBoundComponent();
    self.setDirection(FlexDirection.COLUMN)
        .setSpacing("var(--dwc-space-m)")
        .setPadding("var(--dwc-space-l)");

    Div refCard = card("WebAuthn Options Reference");

    addEntry(refCard, "Authenticator Attachment",
        "Controls which type of authenticator can be used. "
            + "'Platform' restricts to built-in authenticators like Touch ID, Face ID, "
            + "or Windows Hello — convenient but tied to the device. "
            + "'Cross-Platform' requires a roaming authenticator such as a USB security key "
            + "or NFC token — portable across devices. "
            + "'Any' places no restriction.");

    addEntry(refCard, "Resident Key (Discoverable Credential)",
        "Determines whether the credential is stored on the authenticator itself. "
            + "'Discouraged' stores the credential ID server-side; the authenticator holds "
            + "only a key handle. 'Preferred' creates a discoverable credential when supported, "
            + "falling back otherwise. 'Required' mandates a discoverable credential — "
            + "the foundation of true passwordless/usernameless login.");

    addEntry(refCard, "User Verification",
        "Controls whether the authenticator must verify the user (biometric, PIN, pattern). "
            + "'Discouraged' skips verification — useful for second-factor scenarios. "
            + "'Preferred' requests verification but allows without it. "
            + "'Required' mandates verification — use for passwordless flows.");

    addEntry(refCard, "Attestation Conveyance",
        "Controls what the authenticator discloses about itself. "
            + "'None' omits attestation — simplest and most privacy-preserving. "
            + "'Indirect' allows anonymized attestation. "
            + "'Direct' requests raw attestation for make/model verification. "
            + "'Enterprise' requests identifying attestation for managed-device deployments.");

    self.add(refCard);
  }
}
