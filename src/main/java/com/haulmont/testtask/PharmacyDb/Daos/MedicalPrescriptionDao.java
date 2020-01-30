package com.haulmont.testtask.PharmacyDb.Daos;

import com.haulmont.testtask.PharmacyDb.Dtos.MedicalPrescription;

import java.util.List;

public interface MedicalPrescriptionDao {
    MedicalPrescription findMedicalPrescription(long id);

    int insertMedicalPrescription(MedicalPrescription medicalPrescription);

    int updateMedicalPrescription(MedicalPrescription MedicalPrescription);

    int deleteMedicalPrescription(long id);

    List<MedicalPrescription> getAllMedicalPrescriptions();

    List<MedicalPrescription> getMedicalPrescriptionsByDescription(String description);

    List<MedicalPrescription> getMedicalPrescriptionsByPatient(long patientId);

    List<MedicalPrescription> getMedicalPrescriptionsByPriority(byte priority);
}
