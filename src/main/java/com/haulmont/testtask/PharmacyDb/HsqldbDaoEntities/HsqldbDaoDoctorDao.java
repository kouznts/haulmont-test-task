package com.haulmont.testtask.PharmacyDb.HsqldbDaoEntities;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.DaoInterfaces.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.haulmont.testtask.PharmacyDb.DaoablePharmacyDb.DOCTOR;
import static com.haulmont.testtask.SqlHelper.*;

public class HsqldbDaoDoctorDao extends HsqldbDao implements DoctorDao {
    public HsqldbDaoDoctorDao(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public Doctor findDoctor(long id) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s * %s %s %s %s = %s", SELECT, FROM, DOCTOR, WHERE, ID, Long.toString(id));
        ResultSet resultSet = executeQuery(query);

        Doctor doctor = null;
        while (resultSet.next()) {
            doctor = new Doctor(
                    resultSet.getLong(ID),
                    resultSet.getString(FORENAME),
                    resultSet.getString(PATRONYMIC),
                    resultSet.getString(SURNAME),
                    resultSet.getLong(SPECIALIZATION_ID));
        }

        disconnect();
        return doctor;
    }

    @Override
    public int insertDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s %s %s (%s, %s, %s, %s) %s (\'%s\', \'%s\', \'%s\', %s);",
                INSERT, INTO, DOCTOR,
                FORENAME, PATRONYMIC, SURNAME, SPECIALIZATION_ID,
                VALUES,
                doctor.getForename(), doctor.getPatronymic(), doctor.getSurname(), Long.toString(doctor.getSpecializationId()));

        int changedRowsNum = executeUpdate(query);

        disconnect();
        return changedRowsNum;
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
