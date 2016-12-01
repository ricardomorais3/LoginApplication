package org.academiadecodigo.loginSceneBuilder.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by codecadet on 28/11/16.
 */
public class ConnectionManager {

    private String dbUrl;
    private String user;
    private String pass;

    Connection connection = null;

    public ConnectionManager() {
        this.dbUrl = "jdbc:mysql://localhost:3306/users";
        this.user = "root";
        this.pass = "";
    }

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}
