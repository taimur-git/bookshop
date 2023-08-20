package com.bookshop.Models;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBDriver {
    private Connection conn;

    public DBDriver(){
        String url = "jdbc:mysql://localhost:3306/bookstore";
        String userName = "root";
        String password = "";

        try{
            this.conn = DriverManager.getConnection(url, userName, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getUserLogInData(String name, String password){
        PreparedStatement statement;
        ResultSet resultSet = null;
        String sqlQuery = "Select * FROM user WHERE name =? AND password =?";


        try{
            statement = this.conn.prepareStatement(sqlQuery);
            statement.setString(1,name);
            statement.setString(2,password);
            resultSet = statement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getAllItems(){
        Statement statement;
        ResultSet resultSet = null;
        String sqlQuery = "Select * FROM item";


        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getAllMaterials(){
        Statement statement;
        ResultSet resultSet = null;
        String sqlQuery = "Select * FROM materials";
        return null;
    }

    public ResultSet checkUserExists(String userName) {
        PreparedStatement statement;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT id FROM user WHERE name = ?";

        try {
            statement = this.conn.prepareStatement(sqlQuery);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void insertUser(String userName, String userPassword) {
        PreparedStatement statement;
        String sqlQuery = "INSERT INTO user (id, name, password) VALUES (?, ?, ?)";

        try {
            statement = this.conn.prepareStatement(sqlQuery);

            // Generate a unique ID for the user, you can use your preferred method for this
            String userId = generateUserId();

            statement.setString(1, userId);
            statement.setString(2, userName);
            statement.setString(3, userPassword);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generateUserId() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", ""); // Remove dashes
        return uuidStr.substring(0, Math.min(uuidStr.length(), 11));
    }

    public List<Item> itemsList() {
        List<Item> items = new ArrayList<>();
        ResultSet resultSet = getAllItems();
        try {
            while (resultSet.next()) {
                items.add(new Item(
                        resultSet.getInt("item_id"),
                        resultSet.getString("item_name"),
                        resultSet.getInt("item_price"),
                        resultSet.getInt("stock"),
                        resultSet.getString("category"),
                        resultSet.getString("image")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
