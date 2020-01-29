package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoableDoctorSpecialization;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoablePatient;

public interface DaoablePharmacyDb {
    static final String DOCTOR = "doctor";

    DoctorDao getDaoableDoctor();

    DaoableDoctorSpecialization getDaoableDoctorSpecialization();

    DaoableMedicalPrescription getDaoableMedicalPrescription();

    DaoablePatient getDaoablePatient();
}
