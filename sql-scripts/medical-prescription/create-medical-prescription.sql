CREATE TABLE medical_prescription (
  id BIGINT NOT NULL IDENTITY,
  description VARCHAR(1000) NOT NULL,
  patient_id BIGINT NOT NULL,
  doctor_id BIGINT NOT NULL,
  creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  validity_date DATETIME NOT NULL,
  priority TINYINT DEFAULT 3 NOT NULL CHECK(priority > 0 AND priority < 4)
);

ALTER TABLE medical_prescription ADD FOREIGN KEY (patient_id) REFERENCES patient (id);
ALTER TABLE medical_prescription ADD FOREIGN KEY (doctor_id) REFERENCES doctor (id);

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