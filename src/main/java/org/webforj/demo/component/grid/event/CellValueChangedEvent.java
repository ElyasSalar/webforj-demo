package org.webforj.demo.component.grid.event;

import com.webforj.component.element.annotation.EventName;
import com.webforj.component.element.annotation.EventOptions;
import com.webforj.component.event.ComponentEvent;
import org.webforj.demo.component.grid.Grid;

import java.util.Map;

@EventName("cellValueChanged")
@EventOptions(data = {
        @EventOptions.EventData(key = "rowPinned", exp = "event.detail.rowPinned"),
        @EventOptions.EventData(key = "rowIndex", exp = "event.detail.rowIndex"),
        @EventOptions.EventData(key = "value", exp = "event.detail.value"),
        @EventOptions.EventData(key = "source", exp = "event.detail.source"),
        @EventOptions.EventData(key = "newValue", exp = "event.detail.newValue"),
        @EventOptions.EventData(key = "oldValue", exp = "event.detail.oldValue"),
})
public class CellValueChangedEvent extends ComponentEvent<Grid> {
  /**
   * Instantiates a new event.
   *
   * @param component the component
   * @param eventMap  the event map
   */
  public CellValueChangedEvent(Grid component, Map<String, Object> eventMap) {
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

  public Object getSource() {
    return getEventMap().get("source");
  }

  public Object getNewValue() {
    return getEventMap().get("newValue");
  }

  public Object getOldValue() {
    return getEventMap().get("oldValue");
  }
}
