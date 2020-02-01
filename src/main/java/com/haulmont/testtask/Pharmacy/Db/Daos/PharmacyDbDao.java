package com.haulmont.testtask.Pharmacy.Db.Daos;

public interface PharmacyDbDao {
    String DOCTOR = "doctor";
    String DOCTOR_SPECIALIZATION = "doctor_specialization";
    String MEDICAL_PRESCRIPTION = "medical_prescription";
    String PATIENT = "patient";

    DoctorDao getDoctorDao();

    DoctorSpecializationDao getDoctorSpecializationDao();

    MedicalPrescriptionDao getMedicalPrescriptionDao();

    PatientDao getPatientDao();
}
