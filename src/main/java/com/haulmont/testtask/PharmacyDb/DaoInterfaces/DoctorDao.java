package com.haulmont.testtask.PharmacyDb.DaoInterfaces;

import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorDao {
    String ID = "id";
    String FORENAME = "forename";
    String PATRONYMIC = "patronymic";
    String SURNAME = "surname";
    String SPECIALIZATION_ID = "specialization_id";

    Doctor findDoctor(long id) throws SQLException, ClassNotFoundException;

    int insertDoctor(Doctor doctor) throws SQLException, ClassNotFoundException;

    int updateDoctor(Doctor doctor) throws SQLException, ClassNotFoundException;

    boolean deleteDoctor(long id);

    List<Doctor> getAllDoctors();
}
