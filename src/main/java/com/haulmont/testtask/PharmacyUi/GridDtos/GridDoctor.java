package com.haulmont.testtask.PharmacyUi.GridDtos;

import com.haulmont.testtask.PharmacyDb.Daos.DoctorSpecializationDao;
import com.haulmont.testtask.PharmacyDb.Dtos.Doctor;
import com.haulmont.testtask.PharmacyDb.Dtos.DoctorSpecialization;

import java.sql.SQLException;

public class GridDoctor extends Doctor {
    private DoctorSpecializationDao specializationDao;
    private String specializationName;

    public GridDoctor(Doctor doctor, DoctorSpecializationDao specializationDao) {
        this.specializationDao = specializationDao;

        id = doctor.getId();
        forename = doctor.getForename();
        patronymic = doctor.getPatronymic();
        surname = doctor.getSurname();
        setSpecializationId(doctor.getSpecializationId());
    }

    @Override
    public void setSpecializationId(long newSpecializationId) {
        specializationId = newSpecializationId;

        try {
            DoctorSpecialization specialization = specializationDao.findDoctorSpecialization(specializationId);
            specializationName = specialization.getName();
        } catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }
}
