package org.webforj.demo;

import com.webforj.App;
import com.webforj.component.button.Button;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;


public class Demo extends App {
  @Override
  public void run() throws WebforjException {
    Frame window = new Frame();

    final var example = new ExampleComponent();

    example.whenAttached().thenAccept(component -> {
      example.executeJs("console.log(component)");
      example.executeJs("console.log(customElements.get('dwc-example'))");
    });

    final var isDefinedButton = new Button("is defined now?", (event) -> {
      example.executeJs("console.log(customElements.get('dwc-example'))");
    });

    window.add(example, isDefinedButton);
  }
}
