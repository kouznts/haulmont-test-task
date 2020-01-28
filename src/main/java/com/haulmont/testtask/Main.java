package com.haulmont.testtask;

import com.haulmont.testtask.Dao.HsqldbDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String CONNECTION_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    private static final String QUERY = "select * from patient";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HsqldbDao hsqldbDao = new HsqldbDao(CONNECTION_URL);
        hsqldbDao.connect(USER, PASSWORD);

        ResultSet resultSet = null;
        resultSet = hsqldbDao.executeQuery(QUERY);
        
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

        hsqldbDao.disconnect();
    }
}
