package com.bookshop.Models;

public class User {
    private String userID;
    private String name;
    private int admin;

    public User(){

    }

    /*
        Getters
     */
    public int getAdmin() {
        return admin;
    }
    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }



    /* SETTERS **/

    public void setName(String name) {
        this.name = name;
    }


    public void serUserData(String userID,
                            String name,
                            int admin){
        this.userID = userID;
        this.name = name;
        this.admin = admin;
    }

    public String getId() {
        return userID;
    }

    public void clearUserData() {
        this.userID = null;
        this.name = null;
        this.admin = 0;
    }
}
