package com.example.mysterybook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static Connection connection;
    private static DatabaseUtil instance = null;

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = PropertyUtil.getProperty("spring.datasource.url");
            String username = PropertyUtil.getProperty("spring.datasource.username");
            String password = PropertyUtil.getProperty("spring.datasource.password");
            String driver = PropertyUtil.getProperty("spring.datasource.driver-class-name");
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                System.out.println("Error when load driver");
            }
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connect success");
        }
        return connection;
    }

    public static DatabaseUtil getInstance() {
        if (instance == null) {
            instance = new DatabaseUtil();
        }
        return instance;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error when close connection");
            }
        }
    }
}
