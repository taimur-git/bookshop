package com.bookshop.Models;

import com.bookshop.Views.ViewsFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;

public class Model {
/* this basically works as storing session variables */
    private static Model model;
    private final ViewsFactory viewsFactory;
    private final DBDriver dbDriver;
    private final User user;
    private boolean loginFlag;
    private VBox allItems;
    private VBox allCartItems;

    private int lastOrderId;

    private boolean adminFlag;



    private Model() {

        this.viewsFactory = new ViewsFactory();
        this.dbDriver = new DBDriver();
        this.user = new User() ;
        this.allItems = new VBox();
        this.allCartItems = new VBox();

        this.loginFlag = false;
        this.adminFlag = false;
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public VBox getAllCartItems() {
        return allCartItems;
    }


    public ViewsFactory getViewsFactory() {
        return viewsFactory;
    }

    public DBDriver getDbDriver() {
        return dbDriver;
    }

    public User getUser() { return user; }

    public VBox getAllItems() {
        return allItems;
    }





    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public boolean getLoginFlag() {
        return this.loginFlag;
    }
    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
    }
    public boolean getAdminFlag() {
        return this.adminFlag;
    }

    public String getCurrentCustomerId() {
        User currentUser = getUser(); // Get the current user from the model
        if (currentUser != null) {
            return currentUser.getUserID(); // Replace with the method to get the user ID
        }
        return "guest"; // Return a default value or handle missing user
    }

    public void setLastOrderId(int lastOrderId) {
        this.lastOrderId = lastOrderId;
    }

    public int getLastOrderId() {
        return this.lastOrderId;
    }

    public String getUsername() {
        return this.user.getName();
    }
}
