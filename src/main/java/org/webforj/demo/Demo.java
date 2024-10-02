package org.webforj.demo;

import com.webforj.App;
import com.webforj.addons.components.grid.Grid;
import com.webforj.component.button.Button;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Demo extends App {
  @Override
  public void run() throws WebforjException {
    Frame window = new Frame();
    window.setStyle("height", "100%");
    window.setStyle("--dwc-grid-height", "500px");

//    rowData: [
//    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
//    { make: "Ford", model: "F-Series", price: 33850, electric: false },
//    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
//        ],
//    // Column Definitions: Defines the columns to be displayed.
//    columnDefs: [
//    { field: "make", filter: "agStringColumnFilter" },
//    { field: "model", filter: "agStringColumnFilter" },
//    { field: "price", filter: "agNumberColumnFilter" },
//    { field: "electric", filter: "agBooleanColumnFilter" }
//        ],
//    rowHeight: 50,
//            defaultColDef: {
//      flex: 1,
//              editable: true,
//    },
    final var rows = List.of(
            Map.of("make", "Tesla", "model", "Model y", "price", 64950, "electric", true),
            Map.of("make", "Ford", "model", "F-Series", "price", 33850, "electric", false),
            Map.of("make", "Toyota", "model", "Corolla", "price", 29600, "electric", false)
    );
    final var columnDefs = List.of(
            Map.of("field", "make", "filter", "agStringColumnFilter"),
            Map.of("field", "model", "filter", "agStringColumnFilter"),
            Map.of("field", "price", "filter", "agNumberColumnFilter"),
            Map.of("field", "electric", "filter", "agBooleanColumnFilter")
    );
    final var defaultColDef = Map.of(
            "flex", 1, "editable", true
    );

    final var options = new HashMap<String, Object>();
    options.put("rowData", rows);
    options.put("columnDefs", columnDefs);
    options.put("rowHeight", 50);
    options.put("defaultColDef", defaultColDef);
    final var grid = new Grid();
    grid.setOptions(options);

    final var showColumnFilter = new Button("showColumnFilter", (event) -> {
      grid.showColumnFilter("make");
    });
    final var showColumnFilterGrid = new Button("showColumnFilterGrid", (event) -> {
      grid.showColumnFilter("model");
    });

    window.add(grid, showColumnFilter, showColumnFilterGrid);
  }
}
