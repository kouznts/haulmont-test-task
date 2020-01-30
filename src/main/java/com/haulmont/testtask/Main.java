package com.haulmont.testtask;

import com.haulmont.testtask.PharmacyDb.Daos.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Dtos.DoctorSpecialization;
import com.haulmont.testtask.PharmacyDb.Dtos.Patient;
import com.haulmont.testtask.PharmacyDb.HsqldbPharmacyDbDao;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String DB_URL = "jdbc:hsqldb:file:testdb";
    private static final String USER = "SA";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HsqldbPharmacyDbDao hsqldbPharmacyDbDao = new HsqldbPharmacyDbDao(DB_URL, USER, PASSWORD);
        PatientDao patientDao = hsqldbPharmacyDbDao.getPatientDao();
        Patient patient;

        /*patient = patientDao.findPatient(9);
        System.out.println(patient);
        System.out.println();*/

        /*Patient newPatient = new Patient("Михал", "Палыч", "Терентьев", "80000000000");
        patientDao.insertPatient(newPatient);
        patient = patientDao.findPatient(43);
        System.out.println(patient);
        System.out.println();*/

        /*patient = new Patient(0, "Якоб", "Якович", "Якобсон", "100");
        patientDao.updatePatient(patient);
        patient = patientDao.findPatient(0);
        System.out.println(patient);*/

        patientDao.deletePatient(0);
        patient = patientDao.findPatient(0);
        System.out.println(patient);

        List<Patient> patients = patientDao.getAllPatients();
        System.out.println(patients);
    }
}
