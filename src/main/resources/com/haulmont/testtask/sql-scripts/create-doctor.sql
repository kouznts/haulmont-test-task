CREATE TABLE doctor (
  id BIGINT NOT NULL IDENTITY,
  forename VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NULL,
  specialization_id BIGINT NOT NULL
);

ALTER TABLE doctor ADD FOREIGN KEY (specialization_id) REFERENCES doctor_specialization (id);

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

CREATE trigger deleting_doctor
    BEFORE DELETE on doctor
    REFERENCING OLD ROW AS doctor_to_delete
    FOR EACH ROW
    BEGIN ATOMIC
        IF EXISTS(select doctor_id from medical_prescription where doctor_id = doctor_to_delete.id)
            THEN SIGNAL SQLSTATE 'HY008' SET MESSAGE_TEXT = 'cannot delete doctor because he has a medical prescription';
        END IF;
    END