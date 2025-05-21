package org.webforj.views;

import static com.webforj.component.tree.Tree.node;

import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Div;
import com.webforj.component.tree.Tree;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {
  private final Div self = getBoundComponent();
  private final Tree tree = new Tree();

  public HomeView() {
    tree.add(
        node("Documents")
            .setTooltipText("Work and personal documents")
            .add(
                node("Reports")
                    .setTooltipText("Monthly and annual reports")
                    .add(
                        node("2023")
                            .add(
                                node("January.pdf")
                                    .setTooltipText("January report"),
                                node("February.pdf")
                                    .setTooltipText("February report")),
                        node("2022")
                            .add(
                                node("Q4.pdf")
                                    .setTooltipText("Quarter 4 report"),
                                node("Q3.pdf")
                                    .setTooltipText("Quarter 3 report"))),
                node("Invoices")
                    .setTooltipText("Invoices and billing")
                    .add(
                        node("ClientA.pdf")
                            .setTooltipText("Invoice for Client A"),
                        node("ClientB.pdf")
                            .setTooltipText("Invoice for Client B"))),
        node("Pictures")
            .setTooltipText("Photos and images")
            .add(
                node("Vacations")
                    .add(
                        node("Beach.png")
                            .setTooltipText("Beach photo"),
                        node("Mountains.png")
                            .setTooltipText("Mountain photo")),
                node("Events")
                    .add(
                        node("Birthday.jpg")
                            .setTooltipText("Birthday party"))));

    tree.setStyle("margin", "var(--dwc-space-l)")
        .expand("Documents")
        .expand("Pictures")
        .expand("Vacations")
        .selectKey("Mountains.png");

    final var addNodeButton = new Button("Add Node");
    addNodeButton.addClickListener(event -> {
      tree.getRoot().insert(0, node("New Node"));
    });

    self.add(tree, addNodeButton);
  }
}
