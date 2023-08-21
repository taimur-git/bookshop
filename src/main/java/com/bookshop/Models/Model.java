package com.bookshop.Models;

import com.bookshop.Views.ViewsFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;

public class Model {

    private static Model model;
    private final ViewsFactory viewsFactory;
    private final DBDriver dbDriver;
    private final User user;
    private boolean loginFlag;
    private VBox allItems;

    private VBox allCartItems;

    HashSet<String> materialTypes;

    ArrayList<CartItem> cart;

    private ArrayList<Material> materials;

    private Model() {

        this.viewsFactory = new ViewsFactory();
        this.dbDriver = new DBDriver();
        this.user = new User() ;
        materials = new ArrayList<Material>();
        this.allItems = new VBox();
        this.cart = new ArrayList<>();
        this.allCartItems = new VBox();

        this.loginFlag = false;
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

    public float getTotalCost(){
        float total = 0;
        for (CartItem item: cart
             ) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public int getTotalPoints(){
        int total = 0;
        for (CartItem item: cart
        ) {
            total += item.getTotalPoints();
        }
        return total;
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

    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public void addMaterial(Material material){
        materials.add(material);
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public boolean getLoginFlag() {
        return this.loginFlag;
    }

    public String getCurrentCustomerId() {
        User currentUser = getUser(); // Get the current user from the model
        if (currentUser != null) {
            return currentUser.getUserID(); // Replace with the method to get the user ID
        }
        return "guest"; // Return a default value or handle missing user
    }
}
