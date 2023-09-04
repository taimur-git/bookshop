package com.bookshop.Controllers;


import com.bookshop.Models.Server;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController  implements Initializable {

    @FXML
    public ImageView logo;

    @FXML
    public TextField message_send;

    @FXML
    public VBox message_view;

    @FXML
    public ScrollPane sp_main;

    @FXML
    public Button send_btn;

    @FXML
    public Label user_name;

    private Server server;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            server = new Server(new ServerSocket(1234));
            System.out.println("Connected to Client");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error in server.");
        }
        message_view.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        server.receiveMessageFromClient(message_view);

        send_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String messageToSend = message_send.getText();
                if (!messageToSend.isEmpty()){
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.CENTER_RIGHT);
                    hbox.setPadding(new Insets(5,5,5,10));

                    Text text = new Text(messageToSend);
                    TextFlow textflow = new TextFlow(text);
                    textflow.setStyle("-fx-color: rgb(239,242,255);" +
                            "-fx-background-color:rgb(15,125,243);" +
                            "-fx-background-radius: 20px;");
                    textflow.setPadding(new Insets(5,10,5,10));
                    text.setFill(Color.color(0.934,0.945,0.996));

                    hbox.getChildren().add(textflow);
                    message_view.getChildren().add(hbox);

                    server.sendMessageToClient(messageToSend);
                    message_send.clear();
                }
            }
        });
    }

    public static void addLable(String messageFromClient, VBox vbox){
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(messageFromClient);
        TextFlow textflow = new TextFlow(text);
        textflow.setStyle("-fx-background-color:rgb(233,233,235);" +
                "-fx-background-radius: 20px;");
        textflow.setPadding(new Insets(5,10,5,10));
        hbox.getChildren().add(textflow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hbox);
            }
        });
    }
}
