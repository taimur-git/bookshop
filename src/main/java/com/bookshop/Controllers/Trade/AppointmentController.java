package com.bookshop.Controllers.Trade;

import com.bookshop.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {
    public TextField time;
    public DatePicker date;
    public Button confirmButton;
    public TextField location;
    public TextField phoneNumber;
    public TextField lastNAme;
    public TextField firstName;

    public void confirmButtonOnClick() {
        Stage stage = (Stage)time.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showPickUpConfirmationPage();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.confirmButton.setOnMouseClicked(mouseEvent -> confirmButtonOnClick());
    }
}
