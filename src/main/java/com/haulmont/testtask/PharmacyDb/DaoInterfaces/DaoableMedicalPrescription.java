package com.haulmont.testtask.PharmacyDb.DaoInterfaces;

import com.haulmont.testtask.PharmacyDb.Dtos.DtoMedicalPrescription;

import java.util.List;

public interface DaoableMedicalPrescription {
    DtoMedicalPrescription findMedicalPrescription(long id);

    long insertMedicalPrescription(DtoMedicalPrescription medicalPrescription);

    boolean updateMedicalPrescription(DtoMedicalPrescription MedicalPrescription);

    boolean deleteMedicalPrescription(long id);

    List<DtoMedicalPrescription> getAllMedicalPrescriptions();

    List<DtoMedicalPrescription> getMedicalPrescriptionsByDescription(String description);

    List<DtoMedicalPrescription> getMedicalPrescriptionsByPatient(long patientId);

    List<DtoMedicalPrescription> getMedicalPrescriptionsByPriority(byte priority);
}
