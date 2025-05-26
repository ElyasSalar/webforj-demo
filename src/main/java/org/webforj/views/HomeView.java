package org.webforj.views;

import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Div;
import com.webforj.component.progressbar.ProgressBar;
import com.webforj.router.annotation.Route;

@Route("/")
public class HomeView extends Composite<Div> {
  private final Div self = getBoundComponent();

  public HomeView() {
    final var progressBar = new ProgressBar();
    progressBar.setValue(50);
    progressBar.setMax(100);
    progressBar.setTextVisible(true);

    final var setMinButton = new Button("Set Minimum Value to 0");
    setMinButton.addClickListener(event -> {
      progressBar.setMin(0);
    });

    self.add(progressBar, setMinButton);
  }
}
