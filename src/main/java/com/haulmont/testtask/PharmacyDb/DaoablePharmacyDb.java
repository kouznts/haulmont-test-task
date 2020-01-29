package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctor;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctorSpecialization;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoablePatient;

public interface DaoablePharmacyDb {
    static final String DOCTOR = "doctor";

    DaoableDoctor getDaoableDoctor();

    DaoableDoctorSpecialization getDaoableDoctorSpecialization();

    DaoableMedicalPrescription getDaoableMedicalPrescription();

    DaoablePatient getDaoablePatient();
}
