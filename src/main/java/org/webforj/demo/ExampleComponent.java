package org.webforj.demo;

import com.webforj.annotation.InlineJavaScript;
import com.webforj.component.element.ElementComposite;
import com.webforj.component.element.annotation.NodeName;

@InlineJavaScript(value = "context://script.js", top = true)
@NodeName("dwc-example")
public class ExampleComponent extends ElementComposite {

  public void executeJs(String js) {
    getBoundComponent().executeJs(js);
  }
}
