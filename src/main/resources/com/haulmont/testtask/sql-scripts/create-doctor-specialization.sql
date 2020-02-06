CREATE TABLE doctor_specialization (
  id BIGINT NOT NULL IDENTITY,
  name VARCHAR(100) NOT NULL
);

CREATE trigger deleting_doctor_specialization
    BEFORE DELETE on doctor_specialization
    REFERENCING OLD ROW AS specialization_to_delete
    FOR EACH ROW
    BEGIN ATOMIC
        IF EXISTS(select specialization_id from doctor where specialization_id = specialization_to_delete.id)
            THEN SIGNAL SQLSTATE 'HY008' SET MESSAGE_TEXT = 'cannot delete specialization because there is a doctor with such one';
        END IF;
    END