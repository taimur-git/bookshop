package com.bookshop.Models;

public class CheckoutItem {
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
}