package com.dwcj.components.navbar;

import java.util.ArrayList;

public class NavbarItem {
  private String id = "";
  private String icon = "";
  private String caption;
  private String link;
  private Boolean favorite = false;
  private ArrayList<NavbarItem> children;

  public NavbarItem(String id, String icon, String caption, String link, Boolean favorite,
      ArrayList<NavbarItem> children) {
    this.id = id;
    this.icon = icon;
    this.caption = caption;
    this.link = link;
    this.favorite = favorite;
    this.children = children;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Boolean getFavorite() {
    return favorite;
  }

  public void setFavorite(Boolean favorite) {
    this.favorite = favorite;
  }

  public ArrayList<NavbarItem> getChildren() {
    return children;
  }

  public void setChildren(ArrayList<NavbarItem> children) {
    this.children = children;
  }
}
