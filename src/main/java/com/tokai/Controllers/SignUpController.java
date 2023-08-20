package com.tokai.Controllers;

import com.tokai.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


//Handle password missmatch
//Handle unchecked checkBox
//Handle SignUp button
public class SignUpController implements Initializable {
    public FontAwesomeIcon backButton;
    public Text passwordMissMatchText;
    public Text uncheckedCheckBoxText;
    public Button signUpButton;

    public void loginButton() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpButton.setOnMouseClicked(mouseEvent -> signUpButtonOnClick());
        backButton.setOnMouseClicked(event -> backButtonOnClick());
    }

    public void backButtonOnClick(){
        Stage stage = (Stage) this.backButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showLoginWindow();
    }

    public void signUpButtonOnClick(){
        Stage stage = (Stage) this.backButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showLoginWindow();
    }
}
