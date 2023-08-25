package com.bookshop.Controllers;

import com.bookshop.Models.CheckoutItem;
import com.bookshop.Models.Model;
import com.bookshop.Models.Order;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    public Button printOrderButton;
    public Accordion ordersAccordion;

    @FXML
    public void printButtonAction(ActionEvent actionEvent) {

        if(Model.getInstance().getAdminFlag()){
            printAllOrders();
        }else{
            String currentCustomerId = Model.getInstance().getCurrentCustomerId();
            printPerCustomer(currentCustomerId);
        }
    }

    private void printAllOrders() {
        //get all customers
        List<String> customerIds = Model.getInstance().getDbDriver().getAllCustomerIds();
        for ( String customerId : customerIds) {
            printPerCustomer(customerId);
        }
    }

    public void printPerCustomer(String customerId){
        List<Order> orders = Model.getInstance().getDbDriver().getAllOrdersForCustomer(customerId);

        String filename = "output/orders_" + customerId + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            printOrders(writer, "Not Paid", orders, false);
            printOrders(writer, "Paid", orders, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printOrders(PrintWriter writer, String header, List<Order> orders, boolean isPaid) {
        writer.println("==" + header + "==");
        for (Order order : orders) {
            if (order.isPaid() == isPaid) {
                writer.println("Order ID: " + order.getOrderId() + " , BDT " + order.getTotalAmount());
            }
        }
        writer.println();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get orders data and populate the Accordion
        List<Order> orders = Model.getInstance().getAdminFlag() ?
                Model.getInstance().getDbDriver().getAllOrdersForAllCustomers() :
                Model.getInstance().getDbDriver().getAllOrdersForCustomer(Model.getInstance().getCurrentCustomerId());

        if(orders.size() == 0) {
            ordersAccordion.getPanes().add(new TitledPane("No orders found", new VBox()));
        } else {
            for (Order order : orders) {
                TitledPane orderPane = createOrderTitledPane(order);
                ordersAccordion.getPanes().add(orderPane);
            }
        }

    }

    private TitledPane createOrderTitledPane(Order order) {
        VBox content = new VBox();
        content.setSpacing(10);

        Label orderIdLabel = new Label("Order ID: " + order.getOrderId());
        Label customerIdLabel = new Label("Customer ID: " + order.getCustomerId());
        Label paidStatusLabel = new Label("Paid: " + (order.isPaid() ? "Yes" : "No"));
        if(Model.getInstance().getAdminFlag()){
            Button payButton = new Button("Mark as Paid");
            if(order.isPaid()) {
                payButton.setDisable(true);
            }
            payButton.setOnAction(event -> {
                Model.getInstance().getDbDriver().payOrder(order.getOrderId());
                paidStatusLabel.setText("Paid: Yes");
                payButton.setDisable(true);
            });
            content.getChildren().add(payButton);
        }
        Label totalLabel = new Label("Total: BDT " + String.format("%.2f", order.calculateTotal()));

        content.getChildren().addAll(orderIdLabel, customerIdLabel, paidStatusLabel, totalLabel);

        TableView<CheckoutItem> itemsTableView = new TableView<>();
        TableColumn<CheckoutItem, String> itemNameColumn = new TableColumn<>("Item");
        TableColumn<CheckoutItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        TableColumn<CheckoutItem, Double> subtotalColumn = new TableColumn<>("Subtotal");

        itemNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItemName()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));
        subtotalColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSubtotal()));

        itemsTableView.getColumns().addAll(itemNameColumn, quantityColumn, subtotalColumn);
        itemsTableView.setItems(FXCollections.observableArrayList(order.getItems()));

        content.getChildren().add(itemsTableView);

        TitledPane orderPane = new TitledPane("Order #" + order.getOrderId(), content);
        orderPane.setExpanded(false); // Collapse initially

        return orderPane;
    }

}
