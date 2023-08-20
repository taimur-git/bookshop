package com.tokai.Models;

import java.sql.Timestamp;

public class Transactions {

    int userID;
    int quantity;
    float price;
    Timestamp transactionTime;

    Transactions(){

    }

    /* Getters **/

    public int getUserID() {
        return userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    /* Setters **/

    public void setTransactionData(int userID,
                                   int quantity,
                                   float price,
                                   Timestamp timestamp){
        this.userID = userID;
        this.quantity = quantity;
        this.price = price;
        this.transactionTime = timestamp;
    }
}
