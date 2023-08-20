package com.tokai.Controllers.ForgotPassword;

import com.tokai.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewPasswordController implements Initializable {
    public Button resetPasswordButton;
    public Text passwordMissMatchText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetPasswordButton.setOnMouseClicked(mouseEvent -> resetPasswordButtonOnClick());
    }

    public void resetPasswordButtonOnClick() {
        Stage stage = (Stage)passwordMissMatchText.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showLoginWindow();
    }
}
