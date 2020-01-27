package com.haulmont.testtask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static final String URL = "jdbc:hsqldb:file:demodb";
    public static final String USER = "SA";
    public static final String PASSWORD = "";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE users "
                    + "(id BIGINT IDENTITY PRIMARY KEY,"
                    + " username VARCHAR(50));";

    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

           statement.executeUpdate(CREATE_TABLE_QUERY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
