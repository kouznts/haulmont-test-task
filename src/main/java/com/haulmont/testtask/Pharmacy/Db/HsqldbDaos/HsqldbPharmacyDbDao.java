package com.haulmont.testtask.Pharmacy.Db.HsqldbDaos;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.Pharmacy.Db.Daos.*;

public class HsqldbPharmacyDbDao extends HsqldbDao implements PharmacyDbDao {
    public HsqldbPharmacyDbDao(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public DoctorDao getDoctorDao() {
        return new HsqldbDoctorDao(super.dbUrl, super.user, super.password);
    }

    @Override
    public DoctorSpecializationDao getDoctorSpecializationDao() {
        return new HsqldbDoctorSpecializationDao(super.dbUrl, super.user, super.password);
    }

    @Override
    public MedicalPrescriptionDao getMedicalPrescriptionDao() {
        return new HsqldbMedicalPrescriptionDao(super.dbUrl, super.user, super.password);
    }

    @Override
    public PatientDao getPatientDao() {
        return new HsqldbPatientDao(super.dbUrl, super.user, super.password);
    }
}