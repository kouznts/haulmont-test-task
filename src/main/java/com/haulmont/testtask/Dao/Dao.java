package com.haulmont.testtask.Dao;

import java.sql.*;

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

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public boolean execute(final String sqlQuery) throws SQLException {
        boolean result = false;

        if (connection != null) {
            Statement statement = connection.createStatement();
            result = statement.execute(sqlQuery);

            statement.close();
        }

        return result;
    }

    public ResultSet executeQuery(final String sqlQuery) throws SQLException {
        ResultSet resultSet = null;

        if (connection != null) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            statement.close();
        }

        return resultSet;
    }
}