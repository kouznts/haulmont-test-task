package com.haulmont.testtask.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao {
    private String jdbcDriver = null;
    private String connectionUrl = null;

    private Connection connection = null;

    protected Dao(String jdbcDriver, String connectionUrl) {
        this.jdbcDriver = jdbcDriver;
        this.connectionUrl = connectionUrl;
    }

    private void registerDriverManager() {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    protected void connect(String user, String password) {
        registerDriverManager();

        try {
            connection = DriverManager.getConnection(connectionUrl, user, password);
        } catch (SQLException exc) {
            connection = null;
            exc.printStackTrace();
        }
    }

    public void disconnect(Connection connection) {
        try {
            connection.close();
            connection = null;
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public boolean executeQuery(final String sqlQuery) {
        boolean result = false;

        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute(sqlQuery);

                statement.close();
                statement = null;

                result = true;
            }
        } catch (SQLException exc) {
            System.err.println("SQLException: error code " + String.valueOf(exc.getErrorCode()) + ", " + exc.getMessage());
            System.err.println("SQL query: " + sqlQuery);
        }

        return result;
    }
}