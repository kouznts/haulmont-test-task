CREATE TABLE patient (
  id BIGINT NOT NULL IDENTITY PRIMARY KEY,
  forename VARCHAR(100) NOT NULL,
  patronymic VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  phone CHAR(11) NOT NULL
);

CREATE TABLE doctor (
  id BIGINT NOT NULL IDENTITY PRIMARY KEY,
  forename VARCHAR(100) NOT NULL,
  patronymic VARCHAR(100) NOT NULL,
  surname VARCHAR NOT(100) NULL,
  specialization_id BIGINT NOT NULL,
  creation_date DATETIME NOT NULL,
  validity_date DATETIME NOT NULL,
  priority TINYINT(1) NOT NULL DEFAULT 3 CHECK(priority > 0 AND priority < 4),
);

CREATE TABLE doctor_specialization (
  id BIGINT NOT NULL IDENTITY PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
);

CREATE TABLE medical_prescription (
  id BIGINT NOT NULL IDENTITY PRIMARY KEY,
  description TEXT NOT NULL,
  patient_id BIGINT NOT NULL,
  doctor_id BIGINT NOT NULL,
);

ALTER TABLE doctor ADD FOREIGN KEY (specialization_id) REFERENCES doctor_specialization (id);
ALTER TABLE medical_prescription ADD FOREIGN KEY (patient_id) REFERENCES patient (id);
ALTER TABLE medical_prescription ADD FOREIGN KEY (doctor_id) REFERENCES doctor (id);