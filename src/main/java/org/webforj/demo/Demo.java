package org.webforj.demo;

import com.webforj.App;
import com.webforj.annotation.*;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;

@AppAttribute(name = "lang", value = "en")
@Routify(packages = "org.webforj.views")
@AppTitle("Demo")
@AppEntry()
public class Demo extends App {
}
