package org.webforj.demo;

import com.webforj.App;
import com.webforj.annotation.*;

@AppAttribute(name = "lang", value = "en")
@Routify(packages = "org.webforj.views")
@AppTitle("Demo")
@AppEntry()
public class Demo extends App {
}
