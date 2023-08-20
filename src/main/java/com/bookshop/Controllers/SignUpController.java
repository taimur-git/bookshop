package com.bookshop.Controllers;

import com.bookshop.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


//Handle password missmatch
//Handle unchecked checkBox
//Handle SignUp button
public class SignUpController implements Initializable {
    public FontAwesomeIcon backButton;
    public Text passwordMissMatchText;
    public Text uncheckedCheckBoxText;
    public Button signUpButton;
/*
    @FXML

    @FXML

    @FXML
    private
*/public PasswordField password;
public TextField name;
public PasswordField confirm;
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

        String userName = name.getText();
        String userPassword = password.getText();
        String confirmPassword = confirm.getText();
        System.out.println(userName + " " + userPassword + " " + confirmPassword);
        if (!userPassword.equals(confirmPassword)) {
            // Passwords don't match
            // Display an error message or handle it as you see fit
            return;
        }

        ResultSet queryResult = Model.getInstance().getDbDriver().checkUserExists(userName);
        try {
            if (!queryResult.isBeforeFirst()) {
                // User doesn't exist, proceed to insert
                Model.getInstance().getDbDriver().insertUser(userName, userPassword);

                // Close the signup window and show login window
                Stage stage = (Stage) backButton.getScene().getWindow();
                Model.getInstance().getViewsFactory().closeStage(stage);
                Model.getInstance().getViewsFactory().showLoginWindow();
            } else {
                // User already exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

/*
        Stage stage = (Stage) this.backButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showLoginWindow();*/
    }
}
