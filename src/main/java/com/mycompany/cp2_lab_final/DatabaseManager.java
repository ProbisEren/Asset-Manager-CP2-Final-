package com.mycompany.cp2_lab_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseManager {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private Connection connection;

    // git için
    static {
        try (BufferedReader reader = new BufferedReader(new FileReader("db_user_pswrd.txt"))) {
            URL = reader.readLine().trim();
            USER = reader.readLine().trim();
            PASSWORD = reader.readLine().trim();
        } catch (IOException e) {
            System.err.println("Yapılandırma dosyası okunamadı: " + e.getMessage());
        }
    }

    
    public Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Bağlantı hatası: " + e.getMessage());
        }
        return connection;
    }

    
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Bağlantı kapatma hatası: " + e.getMessage());
        }
    }
}
