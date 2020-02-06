CREATE TABLE patient (
  id BIGINT NOT NULL IDENTITY,
  forename VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  phone CHAR(11) NOT NULL
);

CREATE trigger inserting_patient
    BEFORE INSERT ON patient
    REFERENCING NEW ROW AS new_patient
    FOR EACH ROW
    BEGIN ATOMIC
        IF NOT (REGEXP_MATCHES(new_patient.phone, '[0-9]{1,11}'))
            THEN SIGNAL SQLSTATE 'HY008' SET MESSAGE_TEXT = 'wrong phone number';
        END IF;
    END

CREATE trigger deleting_patient
    BEFORE DELETE on patient
    REFERENCING OLD ROW AS patient_to_delete
    FOR EACH ROW
    BEGIN ATOMIC
        IF EXISTS(select patient_id from medical_prescription where patient_id = patient_to_delete.id)
            THEN SIGNAL SQLSTATE 'HY008' SET MESSAGE_TEXT = 'cannot delete patient because he has a medical prescription';
        END IF;
    END