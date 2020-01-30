package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.PharmacyDb.Daos.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Daos.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.Daos.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;

public interface PharmacyDbDao {
    String DOCTOR = "doctor";
    String DOCTOR_SPECIALIZATION = "doctor_specialization";

    DoctorDao getDoctorDao();

    DoctorSpecializationDao getDoctorSpecializationDao();

    DaoableMedicalPrescription getDaoableMedicalPrescription();

    PatientDao getDaoablePatient();
}
