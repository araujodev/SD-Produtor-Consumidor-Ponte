package br.com.ifms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
                Class.forName("org.postgresql.Driver");
                return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "admin");
        } catch (SQLException | ClassNotFoundException e) {	
                throw new RuntimeException(e);
        }	
    }
}


