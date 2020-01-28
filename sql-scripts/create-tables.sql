﻿CREATE TABLE doctor (
  id BIGINT NOT NULL IDENTITY,
  forename VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NULL,
  specialization_id BIGINT NOT NULL
);

CREATE TABLE doctor_specialization (
  id BIGINT NOT NULL IDENTITY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE medical_prescription (
  id BIGINT NOT NULL IDENTITY,
  description VARCHAR(1000) NOT NULL,
  patient_id BIGINT NOT NULL,
  doctor_id BIGINT NOT NULL,
  creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  validity_date DATETIME NOT NULL,
  priority TINYINT DEFAULT 3 NOT NULL CHECK(priority > 0 AND priority < 4)
);

ALTER TABLE doctor ADD FOREIGN KEY (specialization_id) REFERENCES doctor_specialization (id);
ALTER TABLE medical_prescription ADD FOREIGN KEY (patient_id) REFERENCES patient (id);
ALTER TABLE medical_prescription ADD FOREIGN KEY (doctor_id) REFERENCES doctor (id);

CREATE trigger inserting_doctor
    BEFORE INSERT ON doctor
    REFERENCING NEW ROW AS new_doctor
    FOR EACH ROW
    BEGIN ATOMIC
        SET new_doctor.specialization_id = (SELECT id FROM doctor_specialization WHERE id = new_doctor.specialization_id);
    END

CREATE trigger updating_doctor
    BEFORE UPDATE ON doctor
    REFERENCING NEW ROW AS new_doctor
    FOR EACH ROW
    BEGIN ATOMIC
        SET new_doctor.specialization_id = (SELECT id FROM doctor_specialization WHERE id = new_doctor.specialization_id);
    END

CREATE trigger inserting_medical_prescription
    BEFORE INSERT ON medical_prescription
    REFERENCING NEW ROW AS new_medical_prescription
    FOR EACH ROW
    BEGIN ATOMIC
        SET new_medical_prescription.patient_id = (SELECT id FROM patient WHERE id = new_medical_prescription.patient_id);
        SET new_medical_prescription.doctor_id = (SELECT id FROM doctor WHERE id = new_medical_prescription.doctor_id);
    END

CREATE trigger updating_medical_prescription
    BEFORE UPDATE ON medical_prescription
    REFERENCING NEW ROW AS new_medical_prescription
    FOR EACH ROW
    BEGIN ATOMIC
        SET new_medical_prescription.patient_id = (SELECT id FROM patient WHERE id = new_medical_prescription.patient_id);
        SET new_medical_prescription.doctor_id = (SELECT id FROM doctor WHERE id = new_medical_prescription.doctor_id);
    END

CREATE trigger deleting_doctor
    BEFORE DELETE on doctor
    REFERENCING OLD ROW AS doctor_to_delete
    FOR EACH ROW
    BEGIN ATOMIC
        IF EXISTS(select doctor_id from medical_prescription where doctor_id = doctor_to_delete.id)
            THEN SIGNAL SQLSTATE 'HY008' SET MESSAGE_TEXT = 'cannot delete doctor because he has a medical prescription';
        END IF;
    END