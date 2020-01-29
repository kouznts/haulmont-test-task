package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctor;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctorSpecialization;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoablePatient;
import com.haulmont.testtask.PharmacyDb.HsqldbDaoEntities.HsqldbDaoDoctor;

public class HsqldbDaoPharmacy extends HsqldbDao implements DaoablePharmacyDb {
    public HsqldbDaoPharmacy(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public DaoableDoctor getDaoableDoctor() {
        return new HsqldbDaoDoctor(super.dbUrl, super.user, super.password);
    }

    @Override
    public DaoableDoctorSpecialization getDaoableDoctorSpecialization() {
        return new HsqldbDaoDoctorSpecialization();
    }

    @Override
    public DaoableMedicalPrescription getDaoableMedicalPrescription() {
        return new HsqldbDaoMedicalPrescription();
    }

    @Override
    public DaoablePatient getDaoablePatient() {
        return new HsqldbDaoPatient();
    }
}
