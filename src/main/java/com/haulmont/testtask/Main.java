package com.haulmont.testtask;

import java.sql.*;

public class Main {
    private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";

    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    private static final String QUERY = "select * from patient";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // region регистрация драйвера
        Class.forName(JDBC_DRIVER);
        // endregion

        // region создание соединения
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
        // endregion

        // region запрос
        statement = connection.createStatement();
        statement.execute(QUERY);
        // resultSet = statement.executeQuery(QUERY);
        // endregion

        // region вывод результата
        /*while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String forename = resultSet.getString("forename");
            String patronymic = resultSet.getString("patronymic");
            String surname = resultSet.getString("surname");
            String phone = resultSet.getString("phone");

            System.out.println("\n================\n");
            System.out.println(id);
            System.out.println(forename);
            System.out.println(patronymic);
            System.out.println(surname);
            System.out.println(phone);
        }*/
        // endregion

        statement.close();
        connection.close();
    }
}
