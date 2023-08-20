package com.tokai.Controllers.Trade;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CartItemController implements Initializable {
    public HBox cartItemContainer;
    public Text materialName;
    public Text materialQuantity;
    public Text totalPrice;
    public FontAwesomeIcon minusButton;

    public HBox getCartItemContainer() {
        return cartItemContainer;
    }

    public Text getMaterialName() {
        return materialName;
    }

    public Text getTotalPrice() {
        return totalPrice;
    }

    public Text getMaterialQuantity() {
        return materialQuantity;
    }

    public CartItemController(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
