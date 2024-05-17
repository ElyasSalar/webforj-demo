package org.webforj.demo;

import com.webforj.App;
import com.webforj.addons.components.multiselectcombo.MultiSelectCombo;
import com.webforj.addons.components.propertiespanel.PropertiesPanel;
import com.webforj.addons.components.propertiespanel.schema.SchemaGroup;
import com.webforj.addons.components.propertiespanel.schema.StringSchema;
import com.webforj.addons.components.sidemenu.Item;
import com.webforj.addons.components.sidemenu.SideMenu;
import com.webforj.addons.components.suggestionedit.SuggestionEdit;
import com.webforj.annotation.InlineJavaScript;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;

import java.util.List;

@InlineJavaScript(id = "adding-addons-components", value = "context://script.js")
public class Demo extends App {
  @Override
  public void run() throws WebforjException {
    Frame window = new Frame();
    window.setStyle("height", "100%");

    MultiSelectCombo multiSelectCombo = new MultiSelectCombo();
    multiSelectCombo.setPlaceholder("placeholder");

    SuggestionEdit suggestionEdit = new SuggestionEdit();
    suggestionEdit.setPlaceholder("placeholder");

    SideMenu sideMenu = new SideMenu();
    sideMenu.setItems(List.of(
            new Item("1", "info", "Info", "/#", false)
    ));

    PropertiesPanel propertiesPanel = new PropertiesPanel();
    List<SchemaGroup> schemaGroup = List.of(
            new SchemaGroup("Layout", List.of(new StringSchema("margin", "Margin")))
    );
    propertiesPanel.setSchema(schemaGroup);

    FlexLayout content = FlexLayout
            .create(sideMenu, new Div(suggestionEdit, multiSelectCombo), propertiesPanel)
            .justify().between()
            .build().setStyle("height", "100%");

    window.add(content);
  }
}
