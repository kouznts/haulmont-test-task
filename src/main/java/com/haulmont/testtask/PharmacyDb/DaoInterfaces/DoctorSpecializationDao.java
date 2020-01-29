package com.haulmont.testtask.PharmacyDb.DaoInterfaces;

import com.haulmont.testtask.PharmacyDb.Dtos.DoctorSpecialization;

import java.util.List;

public interface DoctorSpecializationDao {
    DoctorSpecialization findDoctorSpecialization(long id);

    long insertDoctorSpecialization(DoctorSpecialization doctorSpecialization);

    boolean updateDoctorSpecialization(DoctorSpecialization doctorSpecialization);

    boolean deleteDoctorSpecialization(long id);

    List<DoctorSpecialization> getAllDoctorSpecializations();
}
