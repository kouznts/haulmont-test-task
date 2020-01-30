package com.haulmont.testtask.PharmacyDb.Daos;

import com.haulmont.testtask.PharmacyDb.Dtos.Patient;

import java.util.List;

public interface PatientDao {
    String ID = "id";
    String FORENAME = "forename";
    String PATRONYMIC = "patronymic";
    String SURNAME = "surname";
    String PHONE = "phone";

    Patient findPatient(long id);

    int insertPatient(Patient patient);

    int updatePatient(Patient patient);

    int deletePatient(long id);

    List<Patient> getAllPatients();
}
