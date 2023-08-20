package com.tokai.Controllers.ForgotPassword;

import com.tokai.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {
    public Button sendInstructionsButton;
    public FontAwesomeIcon backButton;
    public Text emailNotFoundText;

    //Check invalid email

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendInstructionsButton.setOnAction(event-> sendInstructionsButtonOnClick());
        backButton.setOnMouseClicked(mouseEvent -> backButtonOnClick());
    }

    public void backButtonOnClick() {
        Stage stage = (Stage)emailNotFoundText.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showLoginWindow();
    }

    public void sendInstructionsButtonOnClick() {
        Stage stage = (Stage)emailNotFoundText.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showSendEmailWindow();
    }
}
