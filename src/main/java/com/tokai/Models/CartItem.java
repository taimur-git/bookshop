package com.tokai.Models;

public class CartItem {
    String materialName;
    float totalPrice;
    int totalPoints;
    int quantity;


    public CartItem(){

    }

    public String getMaterialName() {
        return materialName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getTotalPoints() { return totalPoints; }

    public void setCartItemData(String name,
                                int quantity,
                                float totalPrice,
                                int totalPoints){
        this.materialName = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalPoints = totalPoints;



    }

    @Override
    public String toString() {
        return "CartItem{" +
                "materialName='" + materialName + '\'' +
                ", totalPrice=" + totalPrice +
                ", quantity=" + quantity +
                '}';
    }
}
