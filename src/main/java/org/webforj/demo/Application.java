package org.webforj.demo;

import com.webforj.App;
import com.webforj.annotation.*;

@Routify(packages = "org.webforj.demo.views")
@AppTitle("Demo app")
@StyleSheet(value = "/static/styles/main.css")
@AppEntry()
public class Application extends App {
}
