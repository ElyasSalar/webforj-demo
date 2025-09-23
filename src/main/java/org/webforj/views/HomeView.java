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

    final var max = new NumberField();
    max.setLabel("Maximum Value");
    max.setPlaceholder("Set maximum value");
    max.addValueChangeListener(event -> {
      final var value = slider.getValue() != null ? slider.getValue().intValue() : 0;
      slider.setMax(value);
    });

    container.add(slider, max);

    self.add(container);
  }
}
