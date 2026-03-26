package org.webforj.views;

import com.webforj.component.Component;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.H3;
import com.webforj.component.html.elements.H4;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.list.ChoiceBox;

import java.util.Map;

/**
 * Shared UI factory methods and constants used across the WebAuthn demo views.
 */
final class DemoComponents {

  private DemoComponents() {}

  static FlexLayout tabLayout(Component... children) {
    FlexLayout layout = new FlexLayout();
    layout.setDirection(FlexDirection.COLUMN)
        .setSpacing("var(--dwc-space-m)")
        .setPadding("var(--dwc-space-l)");
    layout.add(children);
    return layout;
  }

  static Div card(String title, Component... content) {
    Div div = new Div();
    div.addClassName("card");

    H3 heading = new H3(title);
    heading.addClassName("card__title");
    div.add(heading);
    div.add(content);
    return div;
  }

  static void addEntry(Div parent, String title, String description) {
    parent.add(new H4(title), new Paragraph(description));
  }

  @SafeVarargs
  static ChoiceBox createChoice(String label, int defaultIndex,
      Map.Entry<String, String>... items) {
    ChoiceBox box = new ChoiceBox();
    box.setLabel(label);
    for (Map.Entry<String, String> item : items) {
      box.add(item.getKey(), item.getValue());
    }
    box.selectIndex(defaultIndex);
    return box;
  }

  static String selectedKey(ChoiceBox choice) {
    return choice.getSelectedItem().getKey().toString();
  }

  static String truncate(String value, int maxLen) {
    if (value == null) {
      return "N/A";
    }
    return value.length() > maxLen ? value.substring(0, maxLen) + "…" : value;
  }
}
