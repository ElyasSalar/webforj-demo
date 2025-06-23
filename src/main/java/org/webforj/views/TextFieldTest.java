package org.webforj.views;

import com.webforj.component.element.ElementComposite;
import com.webforj.component.element.PropertyDescriptor;
import com.webforj.component.element.annotation.NodeName;

@NodeName("dwc-field")
public class TextFieldTest extends ElementComposite {
  
  
  private final PropertyDescriptor<Boolean> validProp = PropertyDescriptor.attribute("valid", false);

  public boolean isValid() {
    // passing `fromClient` argument as true throws ClassCastException.
    return get(validProp, true);
  }

  public void setValid(boolean valid) {
    set(validProp, valid);
  }
}
