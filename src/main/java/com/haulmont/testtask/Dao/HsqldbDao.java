package com.haulmont.testtask.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HsqldbDao extends Dao {
    private Connection connection = null;

    public HsqldbDao() {
        super("org.hsqldb.jdbc.JDBCDriver");
    }

    @Override
    public void setUrl(String host, String database, int port) {
        this.connectionUrl = "jdbc:hsqldb:hsql://" + host + ":" + port + "/" + database;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void connect(String login, String password) {
        super.connect(login, password);

        try {
            connection = DriverManager.getConnection(connectionUrl, connectionProperties);
        } catch (SQLException exc) {
            connection = null;
            exc.printStackTrace();
        }
    }
}
