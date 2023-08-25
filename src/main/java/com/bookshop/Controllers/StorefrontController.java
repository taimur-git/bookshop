package com.bookshop.Controllers;

import com.bookshop.Models.CheckoutItem;
import com.bookshop.Models.Item;
import com.bookshop.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StorefrontController implements Initializable {

    public FlowPane itemCards;
    public TextField searchField;
    public GridPane itemCardsGrid;

    public Button checkoutButton;
    public Button logOutButton;
    public Button viewOrders;

    private List<Item> itemList; // Store the original list of items
    private ObservableList<Item> displayedItems; // List for displaying filtered items

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList = Model.getInstance().getDbDriver().itemsList();
        displayedItems = FXCollections.observableArrayList(itemList);

        initializeItemCards();

        // Enable live search functionality
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterItems(newValue.toLowerCase());
        });
        checkoutButton.setOnAction(event -> Model.getInstance().getViewsFactory().showCartPage());
    }



    //private void showCartPage() {
    //}

    private void initializeItemCards() {
        /* For FlowPane
        itemCards.getChildren().clear();
        for (Item item : displayedItems) {
            Pane card = createItemCard(item);
            itemCards.getChildren().add(card);
        }

         */
        itemCardsGrid.getChildren().clear();

        int columnCount = 4; // Number of columns in the grid
        int columnIndex = 0;
        int rowIndex = 0;

        for (Item item : displayedItems) {
            Pane card = createItemCard(item);
            itemCardsGrid.add(card, columnIndex, rowIndex);

            columnIndex++;
            if (columnIndex >= columnCount) {
                columnIndex = 0;
                rowIndex++;
            }
        }
        if(Model.getInstance().getAdminFlag()){
            Pane inputCard = createNewItemInputCard();
            itemCardsGrid.add(inputCard, columnIndex, rowIndex);

            columnIndex++;

            Button readFromCSVButton = new Button("Read from CSV");
            readFromCSVButton.setOnAction(event -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open CSV File");

                String initialDirectoryPath = "input";
                File initialDirectory = new File(initialDirectoryPath);
                fileChooser.setInitialDirectory(initialDirectory);

                File csvFile = fileChooser.showOpenDialog(
                        readFromCSVButton.getScene().getWindow()
                );

                if (csvFile != null) {
                    Model.getInstance().getDbDriver().addItemFromCSV(csvFile);

                    itemList = Model.getInstance().getDbDriver().itemsList();
                    displayedItems = FXCollections.observableArrayList(itemList);

                    initializeItemCards();
                }
            });
            itemCardsGrid.add(readFromCSVButton, columnIndex, rowIndex);


        }

    }

    private void filterItems(String query) {
        displayedItems.clear();
        for (Item item : itemList) {
            if (item.getItemName().toLowerCase().contains(query)) {
                displayedItems.add(item);
            }
        }
        initializeItemCards(); // Refresh the displayed item cards
    }




    private Pane createItemCard(Item item) {

        Button minusButton = new Button("-");
        Label quantityLabel = new Label("1");
        Button plusButton = new Button("+");
        minusButton.setOnAction(event -> decreaseQuantity(quantityLabel));
        plusButton.setOnAction(event -> increaseQuantity(quantityLabel));

        BorderPane card = new BorderPane();
        card.getStyleClass().add("item-card");

        Text itemName = new Text(item.getItemName());
        itemName.getStyleClass().add("item-name"); // Apply CSS styling to the name text

        Text itemCategory = new Text("Category: " + item.getCategory());
        itemCategory.getStyleClass().add("item-category"); // Apply CSS styling to the category text

        Text itemPrice = new Text("BDT " + item.getItemPrice());

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> {
            int quantity = Integer.parseInt(quantityLabel.getText());
            addToCart(item, quantity );
            addToCartButton.setDisable(true);
            addToCartButton.setText("Added to Cart");
        });

        HBox bottomBox = new HBox(10); // Adjust spacing as needed
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        bottomBox.getChildren().addAll(minusButton, quantityLabel, plusButton,addToCartButton);

        card.setTop(itemName);
        card.setCenter(itemPrice);
        card.setBottom(bottomBox);
        card.setLeft(itemCategory); // Add the category to the left of the card

        VBox cardContent = new VBox(itemName, itemCategory, itemPrice );
        cardContent.setAlignment(Pos.CENTER); // Align content in the center

        card.setCenter(cardContent);


        return card;
    }

    private Pane createNewItemInputCard() {
        BorderPane card = new BorderPane();
        card.getStyleClass().add("item-card");

        // Text fields for new item details
        TextField newItemNameInput = new TextField();
        newItemNameInput.getStyleClass().add("item-name");
        newItemNameInput.setPromptText("Enter item name");

        TextField newItemPriceInput = new TextField();
        newItemPriceInput.getStyleClass().add("item-price");
        newItemPriceInput.setPromptText("Enter item price");

        TextField newItemStockInput = new TextField();
        newItemStockInput.getStyleClass().add("item-stock");
        newItemStockInput.setPromptText("Enter item stock");

        TextField newItemCategoryInput = new TextField();
        newItemCategoryInput.getStyleClass().add("item-category");
        newItemCategoryInput.setPromptText("Enter item category");

        // Button to add the new item
        Button addItemButton = new Button("Add Item");
        addItemButton.getStyleClass().add("add-item-button");
        addItemButton.setOnAction(event -> {
            String name = newItemNameInput.getText();
            int price = Integer.parseInt(newItemPriceInput.getText());
            int stock = Integer.parseInt(newItemStockInput.getText());
            String category = newItemCategoryInput.getText();

            Model.getInstance().getDbDriver().addItem(name, price, stock, category);
            // Update storefront UI
            itemList = Model.getInstance().getDbDriver().itemsList();
            displayedItems = FXCollections.observableArrayList(itemList);

//            initializeItemCards();
            initializeItemCards();
        });

        // Set up the layout of the input card
        VBox inputContainer = new VBox(newItemNameInput, newItemPriceInput, newItemStockInput, newItemCategoryInput, addItemButton);
        inputContainer.getStyleClass().add("input-container");
        card.setCenter(inputContainer);

        return card;
    }

    private void decreaseQuantity(Label quantityLabel) {
        int currentQuantity = Integer.parseInt(quantityLabel.getText());
        if (currentQuantity > 1) {
            quantityLabel.setText(Integer.toString(currentQuantity - 1));
        }
    }

    private void increaseQuantity(Label quantityLabel) {
        int currentQuantity = Integer.parseInt(quantityLabel.getText());
        quantityLabel.setText(Integer.toString(currentQuantity + 1));
    }

    private void addToCart(Item item, int quantity) {
        // Handle adding item to cart
        String customerId = Model.getInstance().getCurrentCustomerId(); // Retrieve the customer ID from your authentication/session
        int itemId = item.getItemId();
        //int quantity = 1; // Or any desired quantity

        // Add the item to the cart in the database
        Model.getInstance().getDbDriver().addToCart(customerId, itemId, quantity);
/*
        // Disable the "Add to Cart" button to indicate item is in the cart
        Button addToCartButton = createAddToCartButton(item);
        addToCartButton.setDisable(true);
        addToCartButton.setText("Added to Cart");
        */
    }

    private Button createAddToCartButton(Item item) {
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> addToCart(item, 1));
        return addToCartButton;
    }

    public void logoutButtonAction(ActionEvent actionEvent) {
        Model.getInstance().getUser().clearUserData();
Model.getInstance().getViewsFactory().showLoginWindow();

        Stage storefrontStage = (Stage) viewOrders.getScene().getWindow();
        storefrontStage.close();

    }

    public void viewOrdersButtonAction(ActionEvent actionEvent) {
        Model.getInstance().getViewsFactory().showOrdersPage();
    }
}