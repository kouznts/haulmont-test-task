package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPharmacyDbDao;

import java.sql.SQLException;

public class Main {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    private static final String QUERY = "CREATE trigger inserting_patient\n" +
            "    BEFORE INSERT ON patient\n" +
            "    REFERENCING NEW ROW AS new_patient\n" +
            "    FOR EACH ROW\n" +
            "    BEGIN ATOMIC\n" +
            "        IF NOT (REGEXP_MATCHES(new_patient.phone, '[0-9]{1,11}'))\n" +
            "            THEN SIGNAL SQLSTATE 'HY008' SET MESSAGE_TEXT = 'wrong phone number';\n" +
            "        END IF;\n" +
            "    END";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HsqldbPharmacyDbDao hsqldbPharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);

        hsqldbPharmacyDbDao.connect();
        hsqldbPharmacyDbDao.execute(QUERY);
        hsqldbPharmacyDbDao.disconnect();
    }
}
