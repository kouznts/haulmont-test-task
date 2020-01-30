package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.MedicalPrescriptionDao;
import com.haulmont.testtask.PharmacyDb.Dtos.MedicalPrescription;
import com.haulmont.testtask.PharmacyDb.HsqldbPharmacyDbDao;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HsqldbPharmacyDbDao hsqldbPharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);
        MedicalPrescriptionDao medicalPrescriptionDao = hsqldbPharmacyDbDao.getMedicalPrescriptionDao();
        MedicalPrescription medicalPrescription;

        /*medicalPrescription = medicalPrescriptionDao.findMedicalPrescription(9);
        System.out.println(medicalPrescription);
        System.out.println();*/

        /*Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);

        MedicalPrescription newMedicalPrescription = new MedicalPrescription(
                "ТЕСТ", 5, 5, timestamp, timestamp, (byte) 3);
        medicalPrescriptionDao.insertMedicalPrescription(newMedicalPrescription);
        System.out.println(newMedicalPrescription);
        System.out.println();*/

        /*Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);

        medicalPrescription = new MedicalPrescription(
                10, "ТЕСТ-ТЕСТ", 5, 5, timestamp, timestamp, (byte) 2);
        medicalPrescriptionDao.updateMedicalPrescription(medicalPrescription);
        System.out.println(medicalPrescription);*/

        /*medicalPrescriptionDao.deleteMedicalPrescription(10);*/

        List<MedicalPrescription> medicalPrescriptions = medicalPrescriptionDao.getAllMedicalPrescriptions();
        System.out.println(medicalPrescriptions);
    }
}
