package com.haulmont.testtask.PharmacyDb.Daos;

import com.haulmont.testtask.PharmacyDb.Dtos.DtoPatient;

import java.util.List;

public interface PatientDao {
    DtoPatient findPatient(long id);

    long insertPatient(DtoPatient patient);

    boolean updatePatient(DtoPatient patient);

    boolean deletePatient(long id);

    List<DtoPatient> getAllPatients();
}
