package com.haulmont.testtask.PharmacyDb.DaoInterfaces;

import com.haulmont.testtask.PharmacyDb.Dtos.DtoDoctorSpecialization;

import java.util.List;

public interface DaoableDoctorSpecialization {
    DtoDoctorSpecialization findDoctorSpecialization(long id);

    long insertDoctorSpecialization(DtoDoctorSpecialization doctorSpecialization);

    boolean updateDoctorSpecialization(DtoDoctorSpecialization doctorSpecialization);

    boolean deleteDoctorSpecialization(long id);

    List<DtoDoctorSpecialization> getAllDoctorSpecializations();
}
