package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USER = "user";
    private static final String PASS = "user";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
