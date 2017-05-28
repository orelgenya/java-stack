package com.github.orelgenya.jcip.ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author OrelGenya
 */
public class ThreadLocalConfinement {
    public static final String DB_URL = "mock_url";
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
        public Connection initialValue() {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
