package com.bookshop.Controllers.Trade;

import com.bookshop.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;  
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TradeController implements Initializable {


    public Label homeButton;
    public Label projectButton;
    public Label tradeMenuButton;
    public Label donateMenuButton;
    public FontAwesomeIcon rewardsIconButton;
    public Label rewardsButton;
    public FontAwesomeIcon homeIconButton;
    public FontAwesomeIcon userProfileButton;
    public Label pointsLabel;
    public FontAwesomeIcon userDropdownButton;
    public FontAwesomeIcon userNotificationButton;
    public VBox cartItemContainer;
    public Text subtotalText;

    public static Text static_subtotal;
    public Text poinstText;

    public static Text static_pointsText;
    public Text donateButton;
    public Text tradeButton;
    public TextField searchBar;
    public VBox itemContainer;

    private Thread thread;

    public void homeOnClick() {
        Stage stage = (Stage)homeButton.getScene().getWindow();
        this.thread.interrupt();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showUserHomeWindow();
    }

    public void tradeMenuButtonOnClick() {

        Stage stage = (Stage)homeButton.getScene().getWindow();
        this.thread.interrupt();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showTradeWindow();
    }

    public void tradeButtonOnClick() {
        Model.getInstance().getUser().setPoints(Model.getInstance().getUser().getPoints() + Integer.parseInt(this.poinstText.getText()));
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().showPickUpWindow();
    }

    public void donateButtonOnClick() {
        Model.getInstance().getUser().setPoints(Model.getInstance().getUser().getPoints() + Integer.parseInt(this.poinstText.getText()) * 100);
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().showPickUpWindow();
    }

    public Text getSubtotalText() {
        return subtotalText;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeButton.setOnMouseClicked(mouseEvent -> homeOnClick());
        homeIconButton.setOnMouseClicked(mouseEvent -> homeOnClick());
       this.thread =  new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000); // Wait for 1 sec before updating the color
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();//preserve the message
                    return;
                }
                Platform.runLater(()-> this.pointsLabel.setText(Integer.toString(Model.getInstance().getUser().getPoints())));
                System.out.println(Integer.toString(Model.getInstance().getUser().getPoints()));
            }
        });
       this.thread.start();
        tradeButton.setOnMouseClicked(mouseEvent -> tradeButtonOnClick());
        tradeMenuButton.setOnMouseClicked(mouseEvent -> tradeMenuButtonOnClick());
        donateButton.setOnMouseClicked(mouseEvent -> donateButtonOnClick());
        itemContainer.getChildren().add(Model.getInstance().getAllItems());
        this.cartItemContainer.getChildren().add(Model.getInstance().getAllCartItems());
        static_subtotal = this.subtotalText;
        static_pointsText = this.poinstText;



    }
}
