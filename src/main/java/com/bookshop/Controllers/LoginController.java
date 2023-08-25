package com.bookshop.Controllers;

import com.bookshop.Models.Model;
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

public class LoginController implements Initializable {

    public Button loginButton;
    public Button signUPButton;
    public PasswordField password;
    public TextField email;
    //public Label forgotPassword;
    public Text emilOrPasswordIncorrectText;

    //Handle log in credentials
    //Handle checkBox


    public void onLoginButton() {
        String userName = email.getText();
        String userPassword = password.getText();

        ResultSet queryResult = Model.getInstance().getDbDriver().getUserLogInData(userName,userPassword);
        try{
            if(queryResult.isBeforeFirst()){
               queryResult.next();
               boolean isAdmin = queryResult.getInt("admin") == 1;
               Model.getInstance().setAdminFlag(isAdmin);

                Model.getInstance().setLoginFlag(true);
                Model.getInstance().getUser().serUserData(
                        queryResult.getString("id"),
                        queryResult.getString("name"),
                        queryResult.getInt("admin"));

                Stage stage = (Stage)email.getScene().getWindow();
                Model.getInstance().getViewsFactory().closeStage(stage);
                Model.getInstance().getViewsFactory().showStoreFrontPage();
                //if(isAdmin)
                //    Model.getInstance().getViewsFactory().showStoreFrontPage();//.showAdminHomeWindow();

                //showBookshopPage();//showHomePage();//showUserHomeWindow();
            }else{
                this.emilOrPasswordIncorrectText.setText("Invalid Email or password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void onSignUp() {
        Stage stage = (Stage)email.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showSignUPWindow();
    }

    public void onForgotPassword(){
        Stage stage = (Stage)email.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showForgetPasswordWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(event-> onLoginButton());
        signUPButton.setOnMouseClicked(event-> onSignUp());
        //forgotPassword.setOnMouseClicked(event-> onForgotPassword());

    }



}
