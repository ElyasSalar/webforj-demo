package org.webforj.views;

import org.webforj.component.CustomElement;

import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Div;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {

  final Div self = getBoundComponent();

  public HomeView() {
    final var customElement = new CustomElement();
    customElement.setText("Hello from Custom Element!");

    System.out.println("the text of custom element: " + customElement.getText());

    final var printButton = new Button("print text", event -> {
      // it will work here
      System.out.println("the text of custom element inside the button: " + customElement.getText());
    });

    self.add(customElement, printButton);
  }
}
