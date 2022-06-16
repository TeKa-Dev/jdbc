package jm.task.core.jdbc.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static Statement statement;

    static {
        try {statement = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/jdbc", "user", "user")
                    .createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
        return statement;
    }
}
