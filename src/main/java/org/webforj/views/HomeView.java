package org.webforj.views;

import com.webforj.component.Composite;
import com.webforj.component.field.NumberField;
import com.webforj.component.html.elements.Div;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {

  private final Div self = getBoundComponent();

  public HomeView() {
    final var numberField = new NumberField("Enter a number");

    final var minField = new NumberField("Min");
    minField.addValueChangeListener(e -> numberField.setMin(e.getValue()));

    final var maxField = new NumberField("Max");
    maxField.addValueChangeListener(e -> numberField.setMax(e.getValue()));

    final var layout = new Div();
    layout.add(numberField, minField, maxField);

    self.add(layout);
  }
}
