package com.haulmont.testtask.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class Dao {
    protected String jdbcDriver = null;
    protected String connectionUrl = null;
    protected Properties connectionProperties = null;

    public Dao(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    protected void registerDriverManager() {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    public abstract void setUrl(String host, String database, int port);

    public abstract Connection getConnection();

    public void connect(String login, String password) {
        registerDriverManager();

        connectionProperties = new Properties();
        connectionProperties.setProperty("password", password);
        connectionProperties.setProperty("user", login);
        connectionProperties.setProperty("useUnicode", "true");
        connectionProperties.setProperty("characterEncoding", "utf8");
    }

    public void disconnect(Connection connection) {
        try {
            connection.close();
            connection = null;
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public boolean createSchema(final String schema) {
        return false;
    }

    public boolean dropSchema(final String schema) {
        return false;
    }

    public boolean execSqlQuery(final String sqlQuery) {
        boolean result = false;

        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
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