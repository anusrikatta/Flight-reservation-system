package com.flight.dao;

import java.sql.*;

public class DBConnection {
    public static Connection getCon() throws Exception {
        String url = "jdbc:mysql://localhost:3306/vasavi"; // your DB
        String user = "root"; 
        String pass = "root"; 

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
