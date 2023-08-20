package com.bookshop.Controllers;

import com.bookshop.Models.Item;
import com.bookshop.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StorefrontController implements Initializable {

    @FXML
    private FlowPane itemCards;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Fetch items from your database
        // For each item, create an item card and add it to itemCards
        // You'll need to design a method to populate cards, similar to this:
        populateItemCards();
    }

    private void populateItemCards() {
        // For each item in your database, create a card and add it to itemCards
        // Example card creation
        for (Item item : Model.getInstance().getDbDriver().itemsList()) {
            Pane card = createItemCard(item);
            itemCards.getChildren().add(card);
        }
    }

    private Pane createItemCard(Item item) {
        Pane card = new Pane();
        card.getStyleClass().add("item-card"); // Apply CSS styling to the card
/*
        ImageView image = new ImageView(new Image(item.getImagePath()));
        image.setFitWidth(100);
        image.setFitHeight(100);
        */

        Text itemName = new Text(item.getItemName());
        Text itemPrice = new Text("Price: " + item.getItemPrice());

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(event -> addToCart(item));

        // Position and layout elements within the card
        // Add elements to the card pane

        return card;
    }

    private void addToCart(Item item) {
        // Handle adding item to cart
    }
}