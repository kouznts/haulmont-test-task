package com.haulmont.testtask.PharmacyDb.Dtos;

import java.sql.Timestamp;

public class MedicalPrescription {
    private long id;
    private String description;
    private long patientId;
    private long doctorId;
    private Timestamp creationDate;
    private Timestamp validityDate;
    private byte priority;

    public MedicalPrescription(
            String description,
            long patientId,
            long doctorId,
            Timestamp creationDate,
            Timestamp validityDate,
            byte priority) {
        this.id = -1;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.creationDate = creationDate;
        this.validityDate = validityDate;
        this.priority = priority;
    }

    public MedicalPrescription(
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

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public long getPatientId() {
        return patientId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public Timestamp getValidityDate() {
        return validityDate;
    }

    public byte getPriority() {
        return priority;
    }
}
