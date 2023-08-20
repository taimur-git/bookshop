package com.bookshop.Controllers.ForgotPassword;

import com.bookshop.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SendEmailController implements Initializable {
    public ImageView nextButton;
    public FontAwesomeIcon backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nextButton.setOnMouseClicked(event-> nextButtonOnClick());
        this.backButton.setOnMouseClicked(event-> backButtonOnClick());
    }

    public void nextButtonOnClick() {
        Stage stage = (Stage)nextButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showOtpWindow();
    }

    public void backButtonOnClick() {
        Stage stage = (Stage)nextButton.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showForgetPasswordWindow();
    }
}
