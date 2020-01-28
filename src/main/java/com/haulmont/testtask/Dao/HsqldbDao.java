package com.haulmont.testtask.Dao;

public class HsqldbDao extends Dao {
    private static final String JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";

    public HsqldbDao(String connectionUrl) {
        super(JDBC_DRIVER, connectionUrl);
    }
}
