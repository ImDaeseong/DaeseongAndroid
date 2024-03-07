package com.daeseong.gridlayoutmanager_test;

public class Item {

    int image;
    String title;

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    Item(String title, int image) {
        this.title = title;
        this.image = image;
    }
}