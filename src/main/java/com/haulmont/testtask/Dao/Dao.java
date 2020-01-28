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

    public void connect(String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection(connectionUrl, user, password);
    }

    protected void disconnect(Connection connection) throws SQLException {
        connection.close();
    }

    protected boolean executeQuery(final String sqlQuery) throws SQLException {
        boolean result = false;

        if (connection != null) {
            Statement statement = connection.createStatement();
            statement.execute(sqlQuery);

            statement.close();

            result = true;
        }

        return result;
    }
}