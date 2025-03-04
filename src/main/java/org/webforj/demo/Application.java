package org.webforj.demo;

import com.webforj.App;
import com.webforj.annotation.AppEntry;
import com.webforj.annotation.AppTitle;
import com.webforj.annotation.InlineStyleSheet;
import com.webforj.annotation.Routify;

@Routify(packages = "org.webforj.demo.views")
@AppTitle("Demo app")
@InlineStyleSheet(value = "context://styles/main.css")
@AppEntry()
public class Application extends App {
}
