package com.haulmont.testtask.Pharmacy.Db.Daos;

import com.haulmont.testtask.Pharmacy.Db.Dtos.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
    String ID = "id";
    String FORENAME = "forename";
    String PATRONYMIC = "patronymic";
    String SURNAME = "surname";
    String PHONE = "phone";

    Patient findPatient(long id) throws SQLException, ClassNotFoundException;

    int insertPatient(Patient patient) throws SQLException, ClassNotFoundException;

    int updatePatient(Patient patient) throws SQLException, ClassNotFoundException;

    int deletePatient(long id) throws SQLException, ClassNotFoundException;

    List<Patient> getAllPatients() throws SQLException, ClassNotFoundException;
}
