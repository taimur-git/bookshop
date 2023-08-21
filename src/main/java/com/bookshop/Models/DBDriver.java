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

    public void addToCart(String customerId, int itemId, int quantity) {
        String sqlQuery = "INSERT INTO cart (customer_id, item_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, customerId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CheckoutItem> getCartItemsForCustomer(String currentCustomerId) {
        List<CheckoutItem> cartItems = new ArrayList<>();
        String sqlQuery = "SELECT item_name, quantity, item_price " +
                "FROM cart " +
                "JOIN item ON cart.item_id = item.item_id " +
                "WHERE customer_id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, currentCustomerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String itemName = resultSet.getString("item_name");
                    int quantity = resultSet.getInt("quantity");
                    int price = resultSet.getInt("item_price");

                    CheckoutItem checkoutItem = new CheckoutItem(itemName, quantity, price);
                    cartItems.add(checkoutItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }
}
