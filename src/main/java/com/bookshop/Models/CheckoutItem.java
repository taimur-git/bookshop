package com.bookshop.Models;

public class CheckoutItem {
    private final Item item;
    private int quantity;

    public CheckoutItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return item.getItemPriceInt() * quantity;
    }

    public double getPrice() {
        return item.getItemPriceInt();
    }
    public String getItemName() {
        return item.getItemName();
    }

    public int getItemId() {
        return item.getItemId();
    }
    /*
    private String itemName;
    private int quantity;
    private double price;


    public CheckoutItem(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    public void setQuantity(int i) {
        quantity = i;
    }


    public int getItemId() {
    }*/
}