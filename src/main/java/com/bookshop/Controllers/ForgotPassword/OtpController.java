package com.bookshop.Controllers.ForgotPassword;

import com.bookshop.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OtpController implements Initializable {
    public TextArea otp2;
    public TextArea otp1;
    public TextArea otp3;
    public TextArea otp4;
    public HBox otpHbox;
    public Button submitOtpButton;
    public Text wrongOtpText;
    public FontAwesomeIcon backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submitOtpButton.setOnMouseClicked(mouseEvent -> submitOtpButtonOnClick());
        backButton.setOnMouseClicked(mouseEvent -> backButtonOnClick());
    }

    public void submitOtpButtonOnClick(){
        Stage stage = (Stage)otp1.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showCreateNewPasswordWindow();
    }
    public void backButtonOnClick(){
        Stage stage = (Stage)otp1.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showForgetPasswordWindow();
    }
}
