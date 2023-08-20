package com.bookshop.Controllers.Projects;

import com.bookshop.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectsController implements Initializable {

    public Label homeButton;
    public FontAwesomeIcon homeIconButton;
    public Label projectsButton;
    public Label tradeMenuButton;
    public Label donateMenuButton;
    public FontAwesomeIcon rewardsIconButton;
    public Label rewardsButton;
    public FontAwesomeIcon userProfileButton;
    public Label pointsLabel;
    public FontAwesomeIcon notificationButton;

    public void homeOnClick(){
        Stage stage = (Stage)homeButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showUserHomeWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeButton.setOnMouseClicked(mouseEvent -> homeOnClick());
        homeIconButton.setOnMouseClicked(mouseEvent -> homeOnClick());
        this.pointsLabel.setText(Integer.toString(Model.getInstance().getUser().getPoints()));

    }
}
