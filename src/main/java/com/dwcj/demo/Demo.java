package com.dwcj.demo;

import com.dwcj.components.navbar.Navbar;
import com.dwcj.components.navbar.NavbarItem;
import org.dwcj.App;
import org.dwcj.annotation.Attribute;
import org.dwcj.annotation.InlineJavaScript;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.applayout.AppLayout;
import org.dwcj.component.button.Button;
import org.dwcj.component.button.event.ButtonClickEvent;
import org.dwcj.component.window.Frame;
import org.dwcj.component.window.Panel;
import org.dwcj.exceptions.DwcjException;

import java.util.ArrayList;

@InlineJavaScript(
        id = "bbj-navbar",
        value = "context://public/components/bbj-navbar.js",
        attributes = {
                @Attribute(name = "type", value = "module")
        }
)
@InlineStyleSheet(/* css */"""
    bbj-app-layout {
        --bbj-app-layout-drawer-width: 340px;
    }
""")
public class Demo extends App {
    Frame frame;
    Navbar navbar;
    AppLayout appLayout;

    @Override
    public void run() throws DwcjException {
        frame = new Frame();
        navbar = new Navbar();
        appLayout = new AppLayout();

        Panel drawer = new Panel();
        Panel header = new Panel();
        Button drawerExpander = new Button("expand/collapse");

        drawer.add(navbar);
        header.add(drawerExpander);
        drawerExpander.addClickListener(this::handleDrawerExpander);

        ArrayList<NavbarItem> windowChildren = new ArrayList();
        windowChildren.add(new NavbarItem("4", "tabler:alien", "alien", "/window/alien", false, null));
        windowChildren.add(new NavbarItem("3", "tabler:album", "album", "/window/album", true, null));

        ArrayList<NavbarItem> apiChildren = new ArrayList();
        apiChildren.add(new NavbarItem("22", "tabler:biohazard", "biohazard", "/bookmark/api-app/biohazard", true, null));
        apiChildren.add(new NavbarItem("23", "tabler:bluetooth", "bluetooth", "/bookmark/api-app/bluetooth", true, null));
        apiChildren.add(new NavbarItem("24", "tabler:box", "box", "/bookmark/api-app/box", false, null));

        ArrayList<NavbarItem> bookmarkChildren = new ArrayList();
        bookmarkChildren.add(new NavbarItem("9", "tabler:brand-facebook", "facebook", "/bookmark/brand-facebook", true, null));
        bookmarkChildren.add(new NavbarItem("11", "tabler:api-app", "api app", "/bookmark/api-app", false, apiChildren));
        bookmarkChildren.add(new NavbarItem("10", "tabler:beach", "beach", "/bookmark/beach", true, null));

        ArrayList<NavbarItem> navItems = new ArrayList();
        navItems.add(new NavbarItem("1", "tabler:home", "home", "/", false, null));
        navItems.add(new NavbarItem("2", "tabler:window", "window", "/window", false, windowChildren));
        navItems.add(new NavbarItem("5", "tabler:ad", "Ad", "/ad", true, null));
        navItems.add(new NavbarItem("6", "tabler:angle", "Angle", "/angle", true, null));
        navItems.add(new NavbarItem("7", "tabler:bookmark", "bookmark", "/bookmark", false, bookmarkChildren));
        navItems.add(new NavbarItem("8", "tabler:brand-flickr", "flickr", "/brand-flickr", false, null));

        navbar
                .setNavItems(navItems)
                .setShowNewTab(true)
                .setShowFavorites(true)
                .setExpanse(Navbar.Expanse.MEDIUM)
                .setNavItemsTitle("Navigation")
                .setInputPlaceholder("Search");

        appLayout
                .setDrawerWidth("340px")
                .setHeader(header)
                .setDrawer(drawer);

        frame.add(appLayout);
    }

    public void handleDrawerExpander(ButtonClickEvent buttonClickEvent) {
        Boolean isDrawerOpened = this.appLayout.isDrawerOpened();

        this.appLayout.setDrawerOpened(!isDrawerOpened);
    }
}
