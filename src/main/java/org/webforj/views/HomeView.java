package org.webforj.views;

import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Div;
import com.webforj.component.field.TextField;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {

  private final Div self = getBoundComponent();

  public HomeView() {
    final var button = new Button("click");
    button.setPrefixComponent(new Div("prefix"));
    button.setSuffixComponent(new Div("suffix"));

    final var buttonText = new TextField("button text");
    buttonText.setValue(button.getText());
    buttonText.addValueChangeListener(event -> {
      final var value = event.getValue() == null ? "click" : event.getValue();
      button.setText(value);
    });

    self.add(buttonText, button);
  }
}
