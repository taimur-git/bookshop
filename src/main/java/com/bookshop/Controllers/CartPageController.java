package com.bookshop.Controllers;

import com.bookshop.Models.CheckoutItem;
import com.bookshop.Models.Model;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class CartPageController {
    public TableView<CheckoutItem> cartTableView;

    public TableColumn<CheckoutItem, String> itemNameColumn;

    public TableColumn<CheckoutItem, Integer> quantityColumn;
 
    public TableColumn<CheckoutItem, Double> priceColumn;
    public TableColumn<CheckoutItem, Double> subtotalColumn;
    public Label totalLabel;
    public Button checkoutButton;
    private ObservableList<CheckoutItem> checkoutItems;


    public void initialize() {
        // ... Initialize your cartItems ...
        String currentCustomerId = Model.getInstance().getCurrentCustomerId();
        List<CheckoutItem> cartItems = Model.getInstance().getDbDriver().getCartItemsForCustomer(currentCustomerId);

        //ObservableList<CheckoutItem> cartItemsObservableList
        checkoutItems = FXCollections.observableArrayList(cartItems);
        cartTableView.setItems(checkoutItems);

        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        totalLabel.setText("Total: BDT " + totalPrice);
        // Bind table columns to properties
        itemNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItemName()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));
        priceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrice()));
        subtotalColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSubtotal()));

    }

    @FXML
    public void checkoutButtonAction() {
        String customerId = Model.getInstance().getCurrentCustomerId();

        // Step 1: Create order
        Model.getInstance().getDbDriver().createOrder(customerId);

        // Step 2: Insert order items
        int orderId = Model.getInstance().getLastOrderId();//getDbDriver().getLastInsertedOrderId(); // You need to implement this method
        for (CheckoutItem item : checkoutItems) {
            Model.getInstance().getDbDriver().insertOrderItem(orderId, item.getItemId(), item.getQuantity());
        }

        // Step 3: Clear cart
        Model.getInstance().getDbDriver().clearCartForUser(customerId);

        Stage stage = (Stage)checkoutButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
    }
}
