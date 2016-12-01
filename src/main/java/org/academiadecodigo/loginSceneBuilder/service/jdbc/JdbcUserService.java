package org.academiadecodigo.loginSceneBuilder.service.jdbc;

import org.academiadecodigo.loginSceneBuilder.model.User;
import org.academiadecodigo.loginSceneBuilder.persistence.jdbc.ConnectionManager;
import org.academiadecodigo.loginSceneBuilder.service.user.UserService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by codecadet on 28/11/16.
 */
public class JdbcUserService implements UserService {

    //private String serviceName = "JdbcUserService";
    private Connection dbConnection;

    public JdbcUserService(ConnectionManager connectionManager) {
        dbConnection = connectionManager.getConnection();
    }

    @Override
    public boolean authenticate(String username, String password) {
        User tempUser;
        return ((tempUser = findByName(username)) != null) && tempUser.getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {
        Statement statement = null;
        try {
            dbConnection.setAutoCommit(false);
            statement = dbConnection.createStatement();
            String query = "INSERT INTO users(username, password, email) VALUES " +
                    "('"+ user.getUsername() + "', '" + user.getPassword() + "', '" + user.getEmail()+ "')";

            statement.executeUpdate(query);

            dbConnection.commit();

        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public User findByName(String username) {

        // connection and statements
        Statement statement = null;
        User user = null;

        try {
            dbConnection.setAutoCommit(false);
            statement = dbConnection.createStatement();

            String query = "SELECT * FROM users WHERE username = '" + username + "'";

            // execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // user exists
            if (resultSet.next()) {

                String usernameValue = resultSet.getString("username");
                String passwordValue = resultSet.getString("password");
                String emailValue = resultSet.getString("email");

                user = new User(usernameValue, passwordValue, emailValue);
            }

            dbConnection.commit();

        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return user;

    }

    @Override
    public int count() {
        int result = 0;

        // create a new statement
        Statement statement = null;

        try {
            dbConnection.setAutoCommit(false);
            statement = dbConnection.createStatement();

            // create a query
            String query = "SELECT COUNT(*) FROM users";

            // execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // get the results
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

            dbConnection.commit();

        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getName() {
        return UserService.class.getName();
    }
}
