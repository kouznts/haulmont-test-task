package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.Daos.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.Daos.PatientDao;
import com.haulmont.testtask.PharmacyDb.Daos.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Daos.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbDoctorDao;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbDoctorSpecializationDao;

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
    public DaoableMedicalPrescription getDaoableMedicalPrescription() {
        //return new HsqldbDaoMedicalPrescription();
        return null;
    }

    @Override
    public PatientDao getDaoablePatient() {
        //return new HsqldbDaoPatient();
        return null;
    }
}
