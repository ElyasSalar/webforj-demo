package org.webforj.demo.component.grid.event;

import com.webforj.component.element.annotation.EventName;
import com.webforj.component.element.annotation.EventOptions;
import com.webforj.component.event.ComponentEvent;
import org.webforj.demo.component.grid.Grid;

import java.util.Map;

@EventName("cellClicked")
@EventOptions(data = {
        @EventOptions.EventData(key = "rowPinned", exp = "event.detail.rowPinned"),
        @EventOptions.EventData(key = "rowIndex", exp = "event.detail.rowIndex"),
        @EventOptions.EventData(key = "value", exp = "event.detail.value"),
})
public class CellClickedEvent extends ComponentEvent<Grid> {
  /**
   * Instantiates a new event.
   *
   * @param component the component
   * @param eventMap  the event map
   */
  public CellClickedEvent(Grid component, Map<String, Object> eventMap) {
    super(component, eventMap);
  }

  public String getRowPinned() {
    return (String) getEventMap().get("rowPinned");
  }

  public int getRowIndex() {
    return (int) getEventMap().get("rowIndex");
  }

  public Object getValue() {
    return getEventMap().get("value");
  }
}
