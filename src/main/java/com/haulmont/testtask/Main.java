package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;
import com.haulmont.testtask.PharmacyDb.HsqldbDaoPharmacy;

import java.sql.SQLException;

public class Main {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HsqldbDaoPharmacy hsqldbDaoPharmacy = new HsqldbDaoPharmacy(DB_URL, USER, PASSWORD);

        DoctorDao doctorDao = hsqldbDaoPharmacy.getDaoableDoctor();
        Doctor doctor = doctorDao.findDoctor(9);
        System.out.println(doctor);
        System.out.println();

        //Doctor newDoctor = new Doctor("Василий", "Васильевич", "Васильев", 10);
        //doctorDao.insertDoctor(newDoctor);
        doctor = doctorDao.findDoctor(10);
        System.out.println(doctor);
        System.out.println();
    }
}
