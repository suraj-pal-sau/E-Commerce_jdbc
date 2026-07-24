package com.irfan.singleton;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Single instance of DatabaseConnection
    private static DatabaseConnection instance;
    private Connection connection;

    // Private constructor prevents external instantiation
    private DatabaseConnection() throws SQLException {
        // Example: MySQL connection
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "password";

        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connection established.");
    }

    // Public method to provide access to the single instance
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to get the connection object
    public Connection getConnection() {
        return connection;
    }
}

