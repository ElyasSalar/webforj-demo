package org.webforj.component;

import com.webforj.annotation.JavaScript;
import com.webforj.component.element.ElementComposite;
import com.webforj.component.element.annotation.NodeName;

@NodeName("custom-element")
@JavaScript("ws://custom-element.js")
public class CustomElement extends ElementComposite {
  
  public void setText(String text) {
    getBoundComponent().callJsFunction("setText", text);
  }

  public Object getText() {
    return getBoundComponent().callJsFunction("getText");
  }
}
