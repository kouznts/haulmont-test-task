package com.haulmont.testtask.PharmacyDb.DtoEntities;

import java.sql.Timestamp;

public class DtoMedicalPrescription {
    private long id;
    private String description;
    private long patientId;
    private long doctorId;
    private Timestamp creationDate;
    private Timestamp validityDate;
    private byte priority;

    public DtoMedicalPrescription(
            long id,
            String description,
            long patientId,
            long doctorId,
            Timestamp creationDate,
            Timestamp validityDate,
            byte priority) {
        this.id = id;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.creationDate = creationDate;
        this.validityDate = validityDate;
        this.priority = priority;
    }
}
