package com.bookshop.Controllers.Trade;

import com.bookshop.Models.CartItem;
import com.bookshop.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

import static com.bookshop.Controllers.Trade.TradeController.static_pointsText;
import static com.bookshop.Controllers.Trade.TradeController.static_subtotal;

public class ItemController implements Initializable{
    public HBox itemContainer;
    public Text itemName;
    @FXML
    public Text itemUnitPrice;
    @FXML
    public ComboBox amountSelector;

    public FontAwesomeIcon addToCartButton;
    public Text pointsText;

    public ItemController(){

    }

    public HBox getItemContainer() {
        return itemContainer;
    }

    public Text getItemName() {
        return itemName;
    }

    public Text getItemUnitPrice() {
        return itemUnitPrice;
    }

    public Text getPointsText() { return pointsText; }

    public void addToCartButtonOnClick(){

        //creating cartItem obj
        CartItem cartItem = new CartItem();

        //setting data for the obj
        String name = itemName.getText();
        int quantity = Integer.parseInt(amountSelector.getValue().toString());
        float totalPrice = Float.parseFloat(this.itemUnitPrice.getText().split("/",0)[0]) * quantity;
        int totalPoints = Integer.parseInt(this.pointsText.getText()) * quantity;
        cartItem.setCartItemData(name,quantity,totalPrice,totalPoints);
        Model.getInstance().getCart().add(cartItem);
        amountSelector.getSelectionModel().select(0);

        FXMLLoader fxmlLoader = null;
        try{
            //loading cartItem FXML to get its controller class
            fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Trade/CartItem.fxml"));
            HBox hbox = fxmlLoader.load();
            CartItemController cartItemController = fxmlLoader.getController();
            cartItemController.getMaterialName().setText(cartItem.getMaterialName());
            cartItemController.getMaterialQuantity().setText((Integer.toString(cartItem.getQuantity())));
            cartItemController.getTotalPrice().setText(Float.toString(cartItem.getTotalPrice()));

            Model.getInstance().getAllCartItems().getChildren().add(cartItemController.getCartItemContainer());
            System.out.println(Model.getInstance().getTotalCost());
            static_subtotal.setText(Float.toString(Model.getInstance().getTotalCost()));
            static_pointsText.setText(Integer.toString(Model.getInstance().getTotalPoints()));


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void initComboBox(){

        ObservableList<String> list = FXCollections.observableArrayList("0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        this.amountSelector.setItems(list);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addToCartButton.setOnMouseClicked(mouseEvent -> addToCartButtonOnClick());

    }
}
