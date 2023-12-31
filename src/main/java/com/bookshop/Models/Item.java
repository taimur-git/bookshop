package com.bookshop.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Item {
    private int id;
    private String name;
    private int price;
    private int stock;
    private String category;
    private String image;

    public Item(int id, String name, int price, int stock, String category, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.image = image;
    }
    public Item(int itemId, String itemName, int price) {
        this.id = itemId;
        this.name = itemName;
        this.price = price;
    }

    public ObservableValue<String> itemPriceProperty() {
        return new SimpleStringProperty(Integer.toString(price));
    }

    public ObservableValue<String> itemNameProperty() {
        return new SimpleStringProperty(name);
    }

    public String getItemName() {
        return name;
    }

    public String getItemPrice() {
        return Integer.toString(price);
    }
    public int getItemPriceInt() {
        return price;
    }

    public int getItemId() {
        return id;
    }

    public String getImagePath() {
        return image;
    }

    public String getCategory() {
        return category;
    }
}
