package com.bookshop.Models;

import java.util.List;

public class Order {
    private int orderId;
    private String customerId;
    private boolean paid;
    private List<CheckoutItem> items;

    public Order(int orderId, String customerId, boolean paid, List<CheckoutItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.paid = paid;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean isPaid() {
        return paid;
    }

    public List<CheckoutItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(CheckoutItem::getSubtotal)
                .sum();
    }

    public String getTotalAmount() {
        return String.format("%.2f", calculateTotal());
    }
}
