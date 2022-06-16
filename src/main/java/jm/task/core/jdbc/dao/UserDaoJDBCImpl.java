package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        Util.offerQuery("create table users" +
                "(id bigint not null auto_increment," +
                "name varchar(15)," +
                "last_name varchar(15)," +
                "age tinyint," +
                "primary key (id))");
    }

    public void dropUsersTable() {
        Util.offerQuery("drop table users");
    }

    public void saveUser(String name, String lastName, byte age) {
        Util.offerQuery("insert into users (name, last_name, age)" +
                "values (\'" + name + "\', \'"+ lastName + "\', "+ age + ");");
    }

    public void removeUserById(long id) {
        Util.offerQuery("delete from users where id = " + id);
    }

    public void cleanUsersTable() {
        Util.offerQuery("delete from users");
    }

    public List<User> getAllUsers() {
        try (ResultSet allSet = Util.getUsersSet()) {
            List<User> allUsers = new ArrayList<>();

            while (allSet.next()) {
                User user = new User();
                user.setId(allSet.getLong("id"));
                user.setName(allSet.getString("name"));
                user.setLastName(allSet.getString("last_name"));
                user.setAge(allSet.getByte("age"));
                allUsers.add(user);
            }
            return allUsers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Класс Util должен содержать логику настройки соединения с базой данных

    static class Util {

        private static Statement statement;

        static {
            try {
                statement = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/jdbc", "user", "user")
                        .createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private static void offerQuery(String sql) {
            try {
                statement.execute(sql);
            } catch (SQLException e) {
                // ignore
            }
        }

        private static ResultSet getUsersSet() {
            try {
                return statement.executeQuery("select * from users");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
