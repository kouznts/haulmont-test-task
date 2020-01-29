package com.haulmont.testtask.PharmacyDb.DaoableEntities;

import com.haulmont.testtask.PharmacyDb.DtoEntities.DtoDoctor;

import java.sql.SQLException;
import java.util.List;

public interface DaoableDoctor {
    DtoDoctor findDoctor(long id) throws SQLException, ClassNotFoundException;

    long insertDoctor(DtoDoctor doctor);

    boolean updateDoctor(DtoDoctor doctor);

    boolean deleteDoctor(long id);

    List<DtoDoctor> getAllDoctors();
}
