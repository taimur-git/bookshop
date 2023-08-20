package com.bookshop.Controllers.Rewards;

import com.bookshop.Controllers.Trade.ItemController;
import com.bookshop.Models.Material;
import com.bookshop.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RewardsController implements Initializable {

    public Label homeButton;
    public FontAwesomeIcon homeIconButton;
    public Label pointsLabel;
    public Label projectsButton;
    public Label tradeButton;
    public Label donateButton;
    public FontAwesomeIcon userIconButton;
    public FontAwesomeIcon userOptionsButton;
    public FontAwesomeIcon notificationButton;
    public Text rewardItem3;
    public Text rewardItem1;
    public Text rewardItem5;
    public Text rewardItem7;
    public Text rewardItem8;
    public Text rewardItem4;
    public Text rewardItem6;
    public Text rewardItem2;

    private Thread thread;

    public void homeOnClick(){
        Stage stage = (Stage)homeButton.getScene().getWindow();
        this.thread.interrupt();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showUserHomeWindow();
    }

    public void tradeOnClick(){
        if(Model.getInstance().getMaterials().isEmpty())
        {
            ResultSet resultSet = Model.getInstance().getDbDriver().getAllMaterials();
            FXMLLoader fxmlLoader = null;


            try {
                while (resultSet.next()) {
                    String type = resultSet.getString("materialType");
                    String unit = "";
                    Material material = new Material();

                    unit = switch (type) {
                        case "Electronics" -> "/pc";
                        case "Plastic" -> "/kg";
                        case "Cloth" -> "/kg";
                        case "Paper" -> "/kg";
                        case "Scarp metal" -> "/kg";
                        default -> "/kg";
                    };

                    material.setMaterialData(resultSet.getInt("materialID"),
                            resultSet.getString("materialName"),
                            resultSet.getFloat("unitPrice"),
                            unit,
                            resultSet.getString("materialType"),
                            resultSet.getInt("points"));
                    System.out.println(material);
                    Model.getInstance().addMaterial(material);

                    //Adding materials to itemContainer

                    fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Trade/Item.fxml"));
                    HBox hbox = fxmlLoader.load();
                    ItemController itemController = fxmlLoader.getController();

                    itemController.initComboBox();
                    itemController.getItemName().setText(material.getMaterialName());
                    itemController.getItemUnitPrice().setText(material.getMaterialUnitPrice() + material.getMatrialUnit());
                    itemController.getPointsText().setText(Integer.toString(material.getPoints()));
                    Model.getInstance().getAllItems().getChildren().add(itemController.getItemContainer());


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage)homeButton.getScene().getWindow();
        this.thread.interrupt();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showTradeWindow();
    }

    public void onClick(Text text) {

        if(Model.getInstance().getUser().getPoints() <= Integer.parseInt(text.getText().split(" ")[0])){
            Model.getInstance().getViewsFactory().showPointsRedeemedPage();
        }else {
            Model.getInstance().getUser().setPoints(Model.getInstance().getUser().getPoints() - Integer.parseInt(text.getText().split(" ")[0]));
            Model.getInstance().getViewsFactory().showRedeemErrorPage();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeButton.setOnMouseClicked(mouseEvent -> homeOnClick());
        homeIconButton.setOnMouseClicked(mouseEvent -> homeOnClick());
        //this.pointsLabel.setText(Integer.toString(Model.getInstance().getUser().getPoints()));
        this.thread = new Thread(()->{
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
        this.tradeButton.setOnMouseClicked(mouseEvent -> tradeOnClick());
        this.rewardItem1.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem1));
        this.rewardItem2.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem2));
        this.rewardItem3.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem3));
        this.rewardItem4.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem4));
        this.rewardItem5.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem5));
        this.rewardItem6.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem6));
        this.rewardItem7.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem7));
        this.rewardItem8.setOnMouseClicked(mouseEvent -> onClick(this.rewardItem8));
    }



}
