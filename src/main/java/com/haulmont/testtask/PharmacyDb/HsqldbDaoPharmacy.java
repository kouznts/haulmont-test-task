package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctor;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctorSpecialization;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoablePatient;

public class HsqldbDaoPharmacy extends HsqldbDao implements DaoablePharmacyDb {
    public HsqldbDaoPharmacy(String dbUrl) {
        super(dbUrl);
    }

    @Override
    public DaoableDoctor getDaoableDoctor() {
        return HsqldbDaoPatient();
    }

    @Override
    public DaoableDoctorSpecialization getDaoableDoctorSpecialization() {
        return HsqldbDaoDoctorSpecialization();
    }

    @Override
    public DaoableMedicalPrescription getDaoableMedicalPrescription() {
        return HsqldbDaoMedicalPrescription();
    }

    @Override
    public DaoablePatient getDaoablePatient() {
        return HsqldbDaoPatient();
    }
}
