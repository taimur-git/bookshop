package com.bookshop.Controllers;

import com.bookshop.Models.Item;
import com.bookshop.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StorefrontController implements Initializable {

    public FlowPane itemCards;
    public TextField searchField;
    public GridPane itemCardsGrid;

    public Button checkoutButton;

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
        BorderPane card = new BorderPane();
        card.getStyleClass().add("item-card");

        Text itemName = new Text(item.getItemName());
        itemName.getStyleClass().add("item-name"); // Apply CSS styling to the name text
        itemName.setFont(Font.font("Arial", FontPosture.ITALIC, 14)); // Set font and size

        Text itemCategory = new Text("Category: " + item.getCategory());
        itemCategory.getStyleClass().add("item-category"); // Apply CSS styling to the category text

        Text itemPrice = new Text("Price: " + item.getItemPrice());

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> addToCart(item));

        HBox bottomBox = new HBox(addToCartButton);
        bottomBox.getStyleClass().add("item-card-bottom");

        card.setTop(itemName);
        card.setCenter(itemPrice);
        card.setBottom(bottomBox);
        card.setLeft(itemCategory); // Add the category to the left of the card

        VBox cardContent = new VBox(itemName, itemCategory, itemPrice, addToCartButton);
        cardContent.setAlignment(Pos.CENTER); // Align content in the center

        card.setCenter(cardContent);


        return card;
    }

    private void addToCart(Item item) {
        // Handle adding item to cart
        String customerId = Model.getInstance().getCurrentCustomerId(); // Retrieve the customer ID from your authentication/session
        int itemId = item.getItemId();
        int quantity = 1; // Or any desired quantity

        // Add the item to the cart in the database
        Model.getInstance().getDbDriver().addToCart(customerId, itemId, quantity);

        // Disable the "Add to Cart" button to indicate item is in the cart
        Button addToCartButton = createAddToCartButton(item);
        addToCartButton.setDisable(true);
        addToCartButton.setText("Added to Cart");
    }

    private Button createAddToCartButton(Item item) {
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> addToCart(item));
        return addToCartButton;
    }
}