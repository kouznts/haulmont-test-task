package com.haulmont.testtask.PharmacyDb.Dtos;

import java.sql.Timestamp;
import java.util.Date;

public class MedicalPrescription {
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String PATIENT_ID = "patientId";
    public static final String DOCTOR_ID = "doctorId";
    public static final String CREATION_DATE = "creationDate";
    public static final String VALIDITY_DATE = "validityDate";
    public static final String PRIORITY = "priority";

    private long id;
    private String description;
    private long patientId;
    private long doctorId;
    private Date creationDate;
    private Date validityDate;
    private byte priority;

    public MedicalPrescription(
            String description,
            long patientId,
            long doctorId,
            Date creationDate,
            Date validityDate,
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
            Date creationDate,
            Date validityDate,
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public byte getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return Long.toString(id) + '\n' +
                description + '\n' +
                Long.toString(patientId) + '\n' +
                Long.toString(doctorId) + '\n' +
                creationDate + '\n' +
                validityDate + '\n' +
                priority;
    }
}
