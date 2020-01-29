package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoablePatient;

public interface PharmacyDbDao {
    static final String DOCTOR = "doctor";

    DoctorDao getDoctorDao();

    DoctorSpecializationDao getDaoableDoctorSpecialization();

    DaoableMedicalPrescription getDaoableMedicalPrescription();

    DaoablePatient getDaoablePatient();
}
