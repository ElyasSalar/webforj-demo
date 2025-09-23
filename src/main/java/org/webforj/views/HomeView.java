package org.webforj.views;

import com.webforj.component.Composite;
import com.webforj.component.field.NumberField;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.slider.Slider;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {

  private final Div self = getBoundComponent();

  public HomeView() {
    final var container = FlexLayout.create()
        .vertical()
        .align()
        .center()
        .justify()
        .center()
        .build();
    container.setSpacing("40px");
    container.setStyle("height", "100vh");
    container.setStyle("width", "50%");
    container.setStyle("margin", "0 auto");

    final var slider = new Slider();

    final var min = new NumberField();
    min.setLabel("Minimum Value");
    min.setPlaceholder("Set minimum value");
    min.addValueChangeListener(event -> {
      final var value = slider.getValue() != null ? slider.getValue().intValue() : 0;
      slider.setMin(value);
    });

    final var max = new NumberField();
    max.setLabel("Maximum Value");
    max.setPlaceholder("Set maximum value");
    max.addValueChangeListener(event -> {
      final var value = slider.getValue() != null ? slider.getValue().intValue() : 0;
      slider.setMax(value);
    });

    final var value = new NumberField();
    value.setLabel("Current Value");
    value.setPlaceholder("Set current value");
    value.setValue(slider.getValue().doubleValue());
    value.addValueChangeListener(event -> {
      final var v = event.getValue() != null ? event.getValue().intValue() : 0;
      slider.setValue(v);
    });

    container.add(slider, min, max, value);

    self.add(container);
  }
}
