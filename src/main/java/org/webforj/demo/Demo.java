package org.webforj.demo;

import com.webforj.App;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;
import com.webforj.component.optiondialog.FileChooserDialog;


public class Demo extends App {
  @Override
  public void run() throws WebforjException {
    Frame window = new Frame();

    final var fileChooser = new FileChooserDialog("Choose a file");
    fileChooser.setMoveable(false);
    fileChooser.show();
  }
}
