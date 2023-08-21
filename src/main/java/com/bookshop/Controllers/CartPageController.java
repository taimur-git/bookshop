package com.bookshop.Controllers;

import com.bookshop.Models.CheckoutItem;
import com.bookshop.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CartPageController {
    public TableView<CheckoutItem> cartTableView;

    public TableColumn<CheckoutItem, String> itemNameColumn;

    public TableColumn<CheckoutItem, Integer> quantityColumn;
 
    public TableColumn<CheckoutItem, Double> priceColumn;
    public Label totalLabel;

    public Button backButton;
    public void initialize() {
       initializeCartItems();
    }

    private void initializeCartItems() {
        String currentCustomerId = Model.getInstance().getCurrentCustomerId();
        List<CheckoutItem> cartItems = Model.getInstance().getDbDriver().getCartItemsForCustomer(currentCustomerId);
/* resultSet.getString("item_name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("item_price")*/
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("item_price"));

        ObservableList<CheckoutItem> cartItemsObservableList = FXCollections.observableArrayList(cartItems);
        cartTableView.setItems(cartItemsObservableList);

        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        totalLabel.setText("Total: " + totalPrice);
    }

    public void goBackToStorefront(ActionEvent actionEvent) {
    }
}
