package org.webforj.demo.views;

import com.webforj.App;
import com.webforj.component.Composite;
import com.webforj.component.button.Button;
import com.webforj.component.html.elements.Div;
import com.webforj.component.list.ChoiceBox;
import com.webforj.router.annotation.Route;
import org.webforj.demo.component.grid.Grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route("/")
public class HomeView extends Composite<Div> {
  private final Div self = getBoundComponent();

  public HomeView() {
    self.setStyle("height", "100%");

    final var defaultColDef = Map.of(
            "flex", 1, "editable", true
    );

    final var options = new HashMap<String, Object>();
    options.put("rowData", getRows());
    options.put("columnDefs", getColumnDefs());
    options.put("rowHeight", 50);
    options.put("defaultColDef", defaultColDef);
    final var grid = new Grid();
    grid.setOptions(options);

    final var showColumnFilter = new Button("showColumnFilter", (event) -> {
      grid.showColumnFilter("make");
    });
    final var showColumnMenu = new Button("showColumnMenu", event -> {
      grid.showColumnMenu("model");
    });

    grid.addCellClickedListener(event -> {
      App.console().log("cell clicked and value: " + event.getValue() + ", rowIndex: " + event.getRowIndex() + ", rowPinned: " + event.getRowPinned());
    });

    grid.addCellValueChangedListener(event -> {
      App.console().log("cell value changed: " + event.getValue() + ", rowIndex: " + event.getRowIndex() + ", rowPinned: " + event.getRowPinned() + ", source: " + event.getSource() + ", oldValue: " + event.getOldValue() + ", newValue: " + event.getNewValue());
    });

    final var themeSelect = new ChoiceBox();
    themeSelect.add(Grid.Theme.ALPINE, Grid.Theme.ALPINE.name());
    themeSelect.add(Grid.Theme.BALHAM, Grid.Theme.BALHAM.name());
    themeSelect.add(Grid.Theme.QUARTZ, Grid.Theme.QUARTZ.name());
    themeSelect.selectIndex(0);
    themeSelect.addSelectListener((event) -> {
      grid.setTheme((Grid.Theme) themeSelect.getSelectedKey());
    });

    self.add(grid, showColumnFilter, showColumnMenu, themeSelect);
  }

  private List<Map<String, Object>> getColumnDefs() {
    return List.of(
            Map.of("field", "make", "filter", "agStringColumnFilter"),
            Map.of("field", "model", "filter", "agStringColumnFilter"),
            Map.of("field", "price", "filter", "agNumberColumnFilter"),
            Map.of("field", "electric", "filter", "agBooleanColumnFilter")
    );
  }

  private List<Map<String, Object>> getRows() {
    return List.of(
            Map.of("make", "Tesla", "model", "Model y", "price", 64950, "electric", true),
            Map.of("make", "Ford", "model", "F-Series", "price", 33850, "electric", false),
            Map.of("make", "Toyota", "model", "Corolla", "price", 29600, "electric", false),
            Map.of("make", "Honda", "model", "Civic", "price", 28600, "electric", false),
            Map.of("make", "BMW", "model", "X5", "price", 36500, "electric", false),
            Map.of("make", "Mercedes", "model", "E-Class", "price", 41500, "electric", false),
            Map.of("make", "Audi", "model", "A4", "price", 36900, "electric", false),
            Map.of("make", "Volkswagen", "model", "Golf", "price", 25600, "electric", false),
            Map.of("make", "Nissan", "model", "Maxima", "price", 31900, "electric", false),
            Map.of("make", "Hyundai", "model", "Kona", "price", 23600, "electric", false),
            Map.of("make", "Chevrolet", "model", "Camaro", "price", 34900, "electric", false),
            Map.of("make", "Infiniti", "model", "Q50", "price", 39900, "electric", false),
            Map.of("make", "Ford", "model", "Mustang", "price", 46900, "electric", false),
            Map.of("make", "Dodge", "model", "Charger", "price", 28900, "electric", false),
            Map.of("make", "Mercury", "model", "Mariner", "price", 21600, "electric", false),
            Map.of("make", "Mazda", "model", "3", "price", 23600, "electric", false),
            Map.of("make", "Cadillac", "model", "Escalade", "price", 39900, "electric", false),
            Map.of("make", "Buick", "model", "Enclave", "price", 32900, "electric", false),
            Map.of("make", "Honda", "model", "Accord", "price", 25600, "electric", false),
            Map.of("make", "Chevrolet", "model", "Corvette", "price", 49900, "electric", false),
            Map.of("make", "Ford", "model", "Explorer", "price", 42900, "electric", false),
            Map.of("make", "Volkswagen", "model", "Jetta", "price", 28600, "electric", false),
            Map.of("make", "Hyundai", "model", "Santa Fe", "price", 23600, "electric", false),
            Map.of("make", "Nissan", "model", "Altima", "price", 31900, "electric", false),
            Map.of("make", "Toyota", "model", "Camry", "price", 29600, "electric", false),
            Map.of("make", "Ford", "model", "F-150", "price", 46900, "electric", false),
            Map.of("make", "Dodge", "model", "Charger", "price", 28900, "electric", false),
            Map.of("make", "Chevrolet", "model", "Camaro", "price", 34900, "electric", false),
            Map.of("make", "Mazda", "model", "3", "price", 23600, "electric", false),
            Map.of("make", "Ford", "model", "Mustang", "price", 46900, "electric", false),
            Map.of("make", "Ford", "model", "Explorer", "price", 42900, "electric", false),
            Map.of("make", "Volkswagen", "model", "Jetta", "price", 28600, "electric", false),
            Map.of("make", "Hyundai", "model", "Santa Fe", "price", 23600, "electric", false),
            Map.of("make", "Nissan", "model", "Altima", "price", 31900, "electric", false),
            Map.of("make", "Toyota", "model", "Camry", "price", 29600, "electric", false),
            Map.of("make", "Ford", "model", "F-150", "price", 46900, "electric", false),
            Map.of("make", "Dodge", "model", "Charger", "price", 28900, "electric", false)
    );
  }
}
