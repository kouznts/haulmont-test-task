package com.haulmont.testtask.PharmacyDb.DaoInterfaces;

import com.haulmont.testtask.PharmacyDb.Dtos.DtoPatient;

import java.util.List;

public interface DaoablePatient {
    DtoPatient findPatient(long id);

    long insertPatient(DtoPatient patient);

    boolean updatePatient(DtoPatient patient);

    boolean deletePatient(long id);

    List<DtoPatient> getAllPatients();
}
