package com.dwcj.components.navbar.event;

import com.dwcj.components.navbar.Navbar;
import org.dwcj.component.event.Event;
import org.dwcj.component.webcomponent.annotation.EventName;

import java.util.Map;

/**
 * Emitted when the selected item changes.
 *
 * @author ElyasSalar
 */
@EventName("bbj-selected")
public class NavbarSelectedEvent extends Event<Navbar> {

  /**
   * Creates a new event.
   *
   * @param control the control
   * @param eventMap the event map
   */
  public NavbarSelectedEvent(Navbar control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }
}
