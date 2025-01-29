package org.webforj.demo;

import com.webforj.App;
import com.webforj.addons.components.propertiespanel.PropertiesPanel;
import com.webforj.addons.components.propertiespanel.schema.*;
import com.webforj.component.list.ChoiceBox;
import com.webforj.component.list.ListItem;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;
import com.webforj.addons.components.multiselectcombo.MultiSelectCombo;
import com.webforj.addons.components.sidemenu.Item;
import com.webforj.addons.components.sidemenu.SideMenu;
import com.webforj.addons.components.suggestionedit.Suggestion;
import com.webforj.addons.components.suggestionedit.SuggestionEdit;
import com.webforj.annotation.AppEntry;

import java.util.List;
import java.util.stream.Collectors;


@AppEntry()
public class Demo extends App {
  @Override
  public void run() throws WebforjException {
    Frame window = new Frame();

//    final var sideMenu = this.createSideMenu();
//    // full height of the device's screen
//    sideMenu.setStyle("height", "100vh");
//    // or
//    sideMenu.setStyle("height", "700px");
//    // or assuming it's parent has a fixed height since percentage is a relative value
//    sideMenu.setStyle("height", "700%");

//    final var multiSelectCombo = this.createMultiSelectCombo();
//
//    multiSelectCombo.setAllowCustomValue(true);
//    multiSelectCombo.addInputListener(event -> {
//      if (event.getValue().contains("a")) {
//        multiSelectCombo.setValid(true);
//      } else {
//        multiSelectCombo.setValid(false);
//      }
//    });

//    final var appLayout = new AppLayout();
//    appLayout.addToDrawer(sideMenu);
//    appLayout.setDrawerPlacement(AppLayout.DrawerPlacement.LEFT);
//    appLayout.setDrawerOpened(true);
//    appLayout.setStyle("--dwc-app-layout-drawer-width", "300px");
//    sideMenu.setStyle("height", "100vh");
//    sideMenu.setSections(SideMenu.Section.ITEMS, SideMenu.Section.FAVORITES);
//    sideMenu.setI18n(new SideMenuI18n());
//
//    sideMenu.addChangedListener(event -> {
//      App.console().log("selected item: " + event.getSelectedItem().getCaption());
//      if (event.getDeselectedItem().isPresent()) {
//        App.console().log("deselected item: " + event.getDeselectedItem().get().getCaption());
//      }
//    });

//    final var suggestionEdit = createSuggestionEdit();
//    suggestionEdit.setSuggestions(List.of());
//
//    suggestionEdit.addInputListener((event) -> {
//      if (event.getValue().isEmpty()) {
//        return;
//      }
//
//      final var filteredSuggestions = getSuggestions()
//              .stream()
//              .filter(suggestion -> suggestion.getValue().contains(event.getValue()))
//              .toList();
//
//      if (filteredSuggestions.isEmpty()) {
//        return;
//      }
//
//      suggestionEdit.setSuggestions(filteredSuggestions);
//      suggestionEdit.activateItem(0);
//    });
//    suggestionEdit.addOpenedListener((event) -> {
//      App.console().log("dropdown opened");
//      suggestionEdit.activateItem(0);
//    });
//    suggestionEdit.addClosedListener((event) -> {
//      App.console().log("dropdown closed");
//    });
//
//    final var toggleButton = new Button("Toggle", (event) -> {
//      suggestionEdit.open().thenAccept(result -> {
//        suggestionEdit.activateItem(0);
//      });
//    });

    final var propertiesPanel = createPropertiesPanel();

    window.add(propertiesPanel);
  }

  private PropertiesPanel createPropertiesPanel() {
    final var options = List.of(
            new EnumOption("value", "Value", "value"),
            new EnumOption("label", "Label", "label")
    );
    final var schemaProperty1 = new StringSchema("name", "Name");
    final var schemaProperty2 = new BooleanSchema("active", "Active");
    final var schemaProperty3 = new EnumSchema("status", "Status").setOptions(options);
    final var schemaProperty4 = new NumberSchema("age", "Age");

    final var schemaGroup1 = new SchemaGroup("group1");
    schemaGroup1.addProperty(schemaProperty1);
    schemaGroup1.addProperty(schemaProperty2);
    schemaGroup1.addProperty(schemaProperty3);
    schemaGroup1.addProperty(schemaProperty4);

    final var propertiesPanel = new PropertiesPanel();
    propertiesPanel.setSchema(List.of(schemaGroup1, schemaGroup1, schemaGroup1, schemaGroup1));
    propertiesPanel.setStyle("width", "300px");

    return propertiesPanel;
  }

  private SideMenu createSideMenu() {
    final var item1 = new Item("1", "tabler:info", "Info", "/info", false);
    final var item2 = new Item("2", "tabler:calendar", "Calendar", "/calendar", false);
    final var item3 = new Item("3", "tabler:file-text", "Documentation", "/documentation", true);
    final var item4 = new Item("4", "tabler:invoice", "Invoices", "/invoices", false);
    final var item5 = new Item("5", "tabler:chart-bar", "Charts", "/charts", false);
    final var item6 = new Item("6", "tabler:layers", "Layers", "/layers", false);
    final var item7 = new Item("7", "tabler:settings", "Settings", "/settings", false);
    final var item8 = new Item("8", "tabler:user-check", "User Profile", "/user-profile", false);
    final var item9 = new Item("9", "tabler:logout", "Logout", "/logout", false);
    final var item10 = new Item("10", "tabler:plus", "Add New", "/add-new", false);
    final var item11 = new Item("11", "tabler:trash", "Trash", "/trash", false);
    final var item12 = new Item("12", "tabler:question", "Help", "/help", false);
    final var item13 = new Item("13", "tabler:user-plus", "Add User", "/add-user", false);
    final var item14 = new Item("14", "tabler:user-minus", "Remove User", "/remove-user", false);
    final var item15 = new Item("15", "tabler:user-x", "Delete User", "/delete-user", false);
    final var item16 = new Item("16", "tabler:user-edit", "Edit User", "/edit-user", false);
    final var item17 = new Item("17", "tabler:users", "Users", "/users", false);
    final var item18 = new Item("18", "tabler:file-plus", "Add File", "/add-file", false);
    final var item19 = new Item("19", "tabler:file-minus", "Remove File", "/remove-file", false);
    final var item20 = new Item("20", "tabler:file-x", "Delete File", "/delete-file", false);
    final var item21 = new Item("21", "tabler:file-edit", "Edit File", "/edit-file", false);
    final var item22 = new Item("22", "tabler:files", "Files", "/files", false);
    final var item = List.of(
            item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12,
            item13, item14, item15, item16, item17, item18, item19, item20, item21, item22, item13, item14,
            item15, item16, item17, item18, item19, item20, item21, item22, item13, item14, item15, item16,
            item17, item18, item19, item20, item21, item22, item13, item14, item15, item16, item17, item18
    );

    final var sideMenu = new SideMenu();
    sideMenu.setItems(item);
    sideMenu.setSections(SideMenu.Section.ITEMS, SideMenu.Section.FAVORITES);

    return sideMenu;
  }

  private MultiSelectCombo createMultiSelectCombo() {
    final var item1 = new com.webforj.addons.components.multiselectcombo.Item("germany", "Germany");
    final var item2 = new com.webforj.addons.components.multiselectcombo.Item("france", "France");
    final var item3 = new com.webforj.addons.components.multiselectcombo.Item("italy", "Italy");
    final var items = List.of(item1, item2, item3);

    final var multiSelectCombo = new MultiSelectCombo();
    multiSelectCombo.setItems(items);

    return multiSelectCombo;
  }

  private SuggestionEdit createSuggestionEdit() {
    final var suggestion1 = new Suggestion("1", "Banana");
    final var suggestion2 = new Suggestion("2", "Apple");
    final var suggestion3 = new Suggestion("3", "Orange");
    final var suggestions = List.of(suggestion1, suggestion2, suggestion3);

    final var suggestionEdit = new SuggestionEdit();
    suggestionEdit.setSuggestions(suggestions);
    suggestionEdit.setPlaceholder("Enter a country name");

    return suggestionEdit;
  }

  private List<Suggestion> getSuggestions() {
    return List.of(
            new Suggestion("1", "Banana"),
            new Suggestion("2", "Apple"),
            new Suggestion("3", "Orange"),
            new Suggestion("4", "Grapes"),
            new Suggestion("5", "Pineapple"),
            new Suggestion("6", "Strawberry"),
            new Suggestion("7", "Mango"),
            new Suggestion("8", "Cherry"),
            new Suggestion("9", "Watermelon"),
            new Suggestion("10", "Pear")
    );
  }
}
