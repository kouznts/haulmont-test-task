package com.haulmont.testtask.PharmacyDb.HsqldbDaoEntities;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.DaoableEntities.DaoableDoctor;
import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.haulmont.testtask.PharmacyDb.DaoablePharmacyDb.DOCTOR;
import static com.haulmont.testtask.SqlHelper.getSelectAllFromTableWhereAttrEqualsVal;

public class HsqldbDaoDoctor extends HsqldbDao implements DaoableDoctor {
    public HsqldbDaoDoctor(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public Doctor findDoctor(long id) throws SQLException, ClassNotFoundException {
        connect();

        final String query = getSelectAllFromTableWhereAttrEqualsVal(DOCTOR, ID, Long.toString(id));
        ResultSet resultSet = executeQuery(query);

        Doctor doctor = new Doctor(
                resultSet.getLong(ID),
                resultSet.getString(FORENAME),
                resultSet.getString(PATRONYMIC),
                resultSet.getString(SURNAME),
                resultSet.getLong(SPECIALIZATION_ID));

        disconnect();
        return doctor;
    }

    @Override
    public long insertDoctor(Doctor doctor) {
        return 0;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public boolean deleteDoctor(long id) {
        return false;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return null;
    }
}
