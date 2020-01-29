package com.haulmont.testtask.PharmacyDb.DaoableEntities;

import com.haulmont.testtask.PharmacyDb.DtoEntities.DtoPatient;

import java.util.List;

public interface DaoablePatient {
    DtoPatient findPatient(long id);

    long insertPatient(DtoPatient patient);

    boolean updatePatient(DtoPatient patient);

    boolean deletePatient(long id);

    List<DtoPatient> getAllPatients();
}
