package com.haulmont.testtask.PharmacyDb.HsqldbDaos;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.PharmacyDb.Daos.DoctorDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.haulmont.testtask.Dao.SqlHelper.*;
import static com.haulmont.testtask.PharmacyDb.Daos.PharmacyDbDao.DOCTOR;

public class HsqldbDoctorDao extends HsqldbDao implements DoctorDao {
    public HsqldbDoctorDao(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public Doctor findDoctor(long id) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s * %s %s %s %s = %s;", SELECT, FROM, DOCTOR, WHERE, ID, Long.toString(id));
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
    public int updateDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s %s %s " +
                        "%s = \'%s\', " +
                        "%s = \'%s\', " +
                        "%s = \'%s\', " +
                        "%s = %s " +
                        "%s %s = %s;",
                UPDATE, DOCTOR, SET,
                FORENAME, doctor.getForename(),
                PATRONYMIC, doctor.getPatronymic(),
                SURNAME, doctor.getSurname(),
                SPECIALIZATION_ID, Long.toString(doctor.getSpecializationId()),
                WHERE, ID, doctor.getId());

        int changedRowsNum = executeUpdate(query);

        disconnect();
        return changedRowsNum;
    }

    @Override
    public int deleteDoctor(long id) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s %s %s %s %s = %s", DELETE, FROM, DOCTOR, WHERE, ID, Long.toString(id));
        int changedRowsNum = executeUpdate(query);

        disconnect();
        return changedRowsNum;
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s * %s %s", SELECT, FROM, DOCTOR);
        ResultSet resultSet = executeQuery(query);

        List<Doctor> doctors = getDoctorsFromResultSet(resultSet);

        disconnect();
        return doctors;
    }

    private List<Doctor> getDoctorsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Doctor> doctors = new LinkedList<Doctor>();

        Doctor doctor;
        while (resultSet.next()) {
            doctor = new Doctor(
                    resultSet.getLong(ID),
                    resultSet.getString(FORENAME),
                    resultSet.getString(PATRONYMIC),
                    resultSet.getString(SURNAME),
                    resultSet.getLong(SPECIALIZATION_ID));

            doctors.add(doctor);
        }

        return doctors;
    }

    @Override
    public List<Doctor> getDoctorsBySurname(String surname) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s * %s %s " +
                        "%s %s ( %s ) %s %s (\'%s\')",
                SELECT, FROM, DOCTOR,
                WHERE, LOWER, SURNAME, LIKE, LOWER, '%' + surname + '%');

        ResultSet resultSet = executeQuery(query);

        List<Doctor> doctors = getDoctorsFromResultSet(resultSet);

        disconnect();
        return doctors;
    }
}
