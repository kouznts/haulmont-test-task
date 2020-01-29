package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;
import com.haulmont.testtask.PharmacyDb.HsqldbPharmacyDbDao;

import java.sql.SQLException;

public class Main {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HsqldbPharmacyDbDao hsqldbPharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);
        DoctorDao doctorDao = hsqldbPharmacyDbDao.getDoctorDao();
        Doctor doctor;

        /*
        doctor = doctorDao.findDoctor(9);
        System.out.println(doctor);
        System.out.println();*/

        /*Doctor newDoctor = new Doctor("Иван", "Васильевич", "Васильев", 10);
        doctorDao.insertDoctor(newDoctor);
        doctor = doctorDao.findDoctor(11);
        System.out.println(doctor);
        System.out.println();*/

        /*doctor = doctorDao.findDoctor(11);
        System.out.println(doctor);
        System.out.println();

        doctor = new Doctor(11, "Горик", "Васильевич", "Васильев", 3);
        doctorDao.updateDoctor(doctor);
        doctor = doctorDao.findDoctor(11);
        System.out.println(doctor);*/

        doctor = doctorDao.findDoctor(11);
        System.out.println(doctor);
        System.out.println();

        doctorDao.deleteDoctor(11);
        doctor = doctorDao.findDoctor(11);
        System.out.println(doctor);
    }
}
