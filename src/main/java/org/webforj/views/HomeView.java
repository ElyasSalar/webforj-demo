package org.webforj.views;

import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Div;
import com.webforj.component.icons.TablerIcon;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {

  private final Div self = getBoundComponent();

  public HomeView() {
    final var button = new Button("Click me!");
    button.setPrefixComponent(TablerIcon.create("info"));

    final var removePrefixButton = new Button("remove prefix");
    removePrefixButton.addClickListener(event -> button.setPrefixComponent(null));

    self.add(button, removePrefixButton);
  }
}
