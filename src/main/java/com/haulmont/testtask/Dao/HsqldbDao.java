package com.haulmont.testtask.Dao;

public class HsqldbDao extends Dao {
    private static final String JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";

    public HsqldbDao(String dbUrl) {
        super(JDBC_DRIVER, dbUrl);
    }
}
