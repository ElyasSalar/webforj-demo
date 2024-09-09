package org.webforj.demo;

import com.webforj.App;
import com.webforj.Page;
import com.webforj.component.html.elements.Div;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;

public class Demo extends App {
  @Override
  public void run() throws WebforjException {
    Frame window = new Frame();

    final var element = new Div();

    element.getElement().executeJs("console.log('element: executeJs')");
    element.getElement().executeJsAsync("console.log('element: executeJsAsync')"); // throws an exception
    element.getElement().executeJsVoidAsync("console.log('element: executeJsVoidAsync')"); // throws an exception

    Page.getCurrent().executeJs("console.log('Page: executeJs')");
    Page.getCurrent().executeJsAsync("console.log('Page: executeJsAsync')"); // throws an exception
    Page.getCurrent().executeJsVoidAsync("console.log('Page: executeJsVoidAsync')"); // throws an exception

    App.console().log("App"); // throws an exception because it uses executeJsAsync in its underlying implementation

    window.add(element);
  }
}
