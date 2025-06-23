package org.webforj.views;

import com.webforj.component.button.Button;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {
  private final Div self = getBoundComponent();

  public HomeView() {
    final var field = new TextFieldTest();

    final var isValidButton = new Button("Is Valid", (event) -> {
      System.out.println("TextField is valid: " + field.isValid());
    });

    self.add(field, isValidButton);
  }
}
