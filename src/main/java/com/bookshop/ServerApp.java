package com.bookshop;

import com.bookshop.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerApp extends Application {
    @Override
    public void start(Stage stage){
        Model.getInstance().setAdminFlag(true);
        Model.getInstance().getViewsFactory().showStoreFrontPage();
    }
}

