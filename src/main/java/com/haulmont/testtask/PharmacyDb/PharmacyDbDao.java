package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoablePatient;

public interface PharmacyDbDao {
    String DOCTOR = "doctor";
    String DOCTOR_SPECIALIZATION = "doctor_specialization";

    DoctorDao getDoctorDao();

    DoctorSpecializationDao getDoctorSpecializationDao();

    DaoableMedicalPrescription getDaoableMedicalPrescription();

    DaoablePatient getDaoablePatient();
}
