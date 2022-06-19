package jm.task.core.jdbc.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USER = "user";
    private static final String PASS = "user";

    private static Statement statement;

    public static Statement getStatement() {
        if (statement == null) {
            try {
                statement = DriverManager
                        .getConnection(URL, USER, PASS)
                        .createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }
}
