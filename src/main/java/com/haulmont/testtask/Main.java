package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.Dtos.DoctorSpecialization;
import com.haulmont.testtask.PharmacyDb.HsqldbPharmacyDbDao;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HsqldbPharmacyDbDao hsqldbPharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);
        DoctorSpecializationDao doctorSpecializationDao = hsqldbPharmacyDbDao.getDoctorSpecializationDao();
        DoctorSpecialization doctorSpecialization;

        /*doctorSpecialization = doctorSpecializationDao.findDoctorSpecialization(9);
        System.out.println(doctorSpecialization);
        System.out.println();*/

        /*DoctorSpecialization newDoctorSpecialization = new DoctorSpecialization("Иван");
        doctorSpecializationDao.insertDoctorSpecialization(newDoctorSpecialization);
        doctorSpecialization = doctorSpecializationDao.findDoctorSpecialization(11);
        System.out.println(doctorSpecialization);
        System.out.println();*/

        /*doctorSpecialization = new DoctorSpecialization(0, "Горик");
        doctorSpecializationDao.updateDoctorSpecialization(doctorSpecialization);
        doctorSpecialization = doctorSpecializationDao.findDoctorSpecialization(0);
        System.out.println(doctorSpecialization);*/

        doctorSpecializationDao.deleteDoctorSpecialization(0);
        doctorSpecialization = doctorSpecializationDao.findDoctorSpecialization(0);
        System.out.println(doctorSpecialization);

        List<DoctorSpecialization> doctorSpecializations = doctorSpecializationDao.getAllDoctorSpecializations();
        System.out.println(doctorSpecializations);
    }
}
