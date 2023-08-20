package com.tokai.Controllers.User;

import com.tokai.Controllers.Trade.ItemController;
import com.tokai.Models.Material;
import com.tokai.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserHomePageController implements Initializable {
    public FontAwesomeIcon homeIconButton;
    public Label homeButton;
    public FontAwesomeIcon notificationButton;
    public Label pointsLabel;

    public static Label static_pointsLabel;
    public FontAwesomeIcon userProfileButton;
    public Label projectsButton;
    public FontAwesomeIcon rewardsIconButton;
    public Label rewardsButton;
    public Label tradeMenuButton;
    public Label donateMenuButton;
    public ImageView donateButton;
    public ImageView tradeButton;
    public HBox menuBar;

    public void homeOnClick(){

    }

    public void rewardsOnClick(){
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showRewardsPage();
    }

    public void projectsOnClick(){
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showProjectssPage();
    }


    public void tradeButtonOnClick() {
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
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showTradeWindow();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeButton.setOnMouseClicked(mouseEvent -> homeOnClick());
        homeIconButton.setOnMouseClicked(mouseEvent -> homeOnClick());
        tradeButton.setOnMouseClicked(mouseEvent -> tradeButtonOnClick());
        tradeMenuButton.setOnMouseClicked(mouseEvent -> tradeButtonOnClick());
        projectsButton.setOnMouseClicked(mouseEvent -> projectsOnClick());
        rewardsButton.setOnMouseClicked(mouseEvent -> rewardsOnClick());
        rewardsIconButton.setOnMouseClicked(mouseEvent -> rewardsOnClick());
        donateMenuButton.setOnMouseClicked(mouseEvent -> tradeButtonOnClick());
        donateButton.setOnMouseClicked(mouseEvent -> tradeButtonOnClick());
        this.pointsLabel.setText(Integer.toString(Model.getInstance().getUser().getPoints()));
    }
}
