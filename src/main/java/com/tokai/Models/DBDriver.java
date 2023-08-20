package com.tokai.Models;


import javax.xml.transform.Result;
import java.sql.*;

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
    public ResultSet getAllMaterials(){
        Statement statement;
        ResultSet resultSet = null;
        String sqlQuery = "Select * FROM material";


        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
