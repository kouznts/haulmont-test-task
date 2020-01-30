package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.Daos.MedicalPrescriptionDao;
import com.haulmont.testtask.PharmacyDb.Daos.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Daos.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbDoctorDao;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbDoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbPatientDao;

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
    public MedicalPrescriptionDao getDaoableMedicalPrescription() {
        //return new HsqldbDaoMedicalPrescription();
        return null;
    }

    @Override
    public PatientDao getPatientDao() {
        return new HsqldbPatientDao(super.dbUrl, super.user, super.password);
    }
}
