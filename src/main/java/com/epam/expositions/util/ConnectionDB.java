package com.epam.expositions.util;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {
    private static Connection connection = null;

    @SneakyThrows
    public static Connection getConnection() {

        if (connection != null) {
            return connection;
        }

        FileInputStream fileInputStream = new FileInputStream("src/main/resources/database.properties");

        Properties properties = new Properties();
        properties.load(fileInputStream);

        final String DATABASE_URL = properties.getProperty("url");
        final String DATABASE_USERNAME = properties.getProperty("username");
        final String DATABASE_PASS = properties.getProperty("password");

        connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
        return connection;

    }

}
