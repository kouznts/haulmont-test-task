package com.haulmont.testtask.PharmacyDb;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoableDoctorSpecialization;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoableMedicalPrescription;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DaoablePatient;
import com.haulmont.testtask.PharmacyDb.HsqldbDaos.HsqldbDoctorDao;

public class HsqldbPharmacyDbDao extends HsqldbDao implements PharmacyDbDao {
    public HsqldbPharmacyDbDao(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public DoctorDao getDoctorDao() {
        return new HsqldbDoctorDao(super.dbUrl, super.user, super.password);
    }

    @Override
    public DaoableDoctorSpecialization getDaoableDoctorSpecialization() {
        //return new HsqldbDaoDoctorSpecialization();
        return null;
    }

    @Override
    public DaoableMedicalPrescription getDaoableMedicalPrescription() {
        //return new HsqldbDaoMedicalPrescription();
        return null;
    }

    @Override
    public DaoablePatient getDaoablePatient() {
        //return new HsqldbDaoPatient();
        return null;
    }
}
