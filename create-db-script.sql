CREATE DATABASE pharmacy CHARACTER SET utf8;

USE pharmacy;

CREATE TABLE patient (
  id BIGINT NOT NULL AUTO_INCREMENT,
  forename VARCHAR(100) NOT NULL,
  patronymic VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  phone CHAR(11) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE doctor (
  id BIGINT NOT NULL AUTO_INCREMENT,
  forename VARCHAR(100) NOT NULL,
  patronymic VARCHAR(100) NOT NULL,
  surname VARCHAR NOT(100) NULL,
  specialization_id BIGINT NOT NULL,
  creation_date DATETIME NOT NULL,
  validity_date DATETIME NOT NULL,
  priority TINYINT(1) NOT NULL DEFAULT 3 CHECK(priority > 0 AND priority < 4),
  PRIMARY KEY (id)
);

CREATE TABLE doctor_specialization (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE medical_prescription (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description TEXT NOT NULL,
  patient_id BIGINT NOT NULL,
  doctor_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE doctor ADD FOREIGN KEY (specialization_id) REFERENCES doctor_specialization (id);
ALTER TABLE medical_prescription ADD FOREIGN KEY (patient_id) REFERENCES patient (id);
ALTER TABLE medical_prescription ADD FOREIGN KEY (doctor_id) REFERENCES doctor (id);