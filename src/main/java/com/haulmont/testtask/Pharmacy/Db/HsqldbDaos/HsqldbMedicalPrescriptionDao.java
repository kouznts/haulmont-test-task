package com.haulmont.testtask.Pharmacy.Db.HsqldbDaos;

import com.haulmont.testtask.Dao.HsqldbDao;
import com.haulmont.testtask.Pharmacy.Db.Daos.MedicalPrescriptionDao;
import com.haulmont.testtask.Pharmacy.Db.Dtos.MedicalPrescription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.haulmont.testtask.Pharmacy.Db.Daos.PharmacyDbDao.MEDICAL_PRESCRIPTION;
import static com.haulmont.testtask.SqlHelper.*;

public class HsqldbMedicalPrescriptionDao extends HsqldbDao implements MedicalPrescriptionDao {
    public HsqldbMedicalPrescriptionDao(String dbUrl, String user, String password) {
        super(dbUrl, user, password);
    }

    @Override
    public MedicalPrescription findMedicalPrescription(long id) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s * %s %s %s %s = %s;",
                SELECT, FROM, MEDICAL_PRESCRIPTION, WHERE, ID, Long.toString(id));

        ResultSet resultSet = executeQuery(query);

        MedicalPrescription medicalPrescription = null;
        while (resultSet.next()) {
            medicalPrescription = new MedicalPrescription(
                    resultSet.getLong(ID),
                    resultSet.getString(DESCRIPTION),
                    resultSet.getLong(PATIENT_ID),
                    resultSet.getLong(DOCTOR_ID),
                    resultSet.getTimestamp(CREATION_DATE),
                    resultSet.getTimestamp(VALIDITY_DATE),
                    resultSet.getByte(PRIORITY));
        }

        disconnect();
        return medicalPrescription;
    }

    @Override
    public int insertMedicalPrescription(MedicalPrescription medicalPrescription) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s %s %s (%s, %s, %s, %s, %s, %s) %s (\'%s\', %s, %s, %s \'%s\', %s \'%s\', %s);",
                INSERT, INTO, MEDICAL_PRESCRIPTION,
                DESCRIPTION, PATIENT_ID, DOCTOR_ID, CREATION_DATE, VALIDITY_DATE, PRIORITY,
                VALUES,
                medicalPrescription.getDescription(), medicalPrescription.getPatientId(), medicalPrescription.getDoctorId(),
                TIMESTAMP, medicalPrescription.getCreationDate(),
                TIMESTAMP, medicalPrescription.getValidityDate(),
                medicalPrescription.getPriority());

        int changedRowsNum = executeUpdate(query);

        disconnect();
        return changedRowsNum;
    }

    @Override
    public int updateMedicalPrescription(MedicalPrescription medicalPrescription) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s %s %s " +
                        "%s = \'%s\', " +
                        "%s = %s, " +
                        "%s = %s, " +
                        "%s = %s \'%s\', " +
                        "%s = %s \'%s\', " +
                        "%s = %s " +
                        "%s %s = %s;",
                UPDATE, MEDICAL_PRESCRIPTION, SET,
                DESCRIPTION, medicalPrescription.getDescription(),
                PATIENT_ID, medicalPrescription.getPatientId(),
                DOCTOR_ID, medicalPrescription.getDoctorId(),
                CREATION_DATE, TIMESTAMP, medicalPrescription.getCreationDate(),
                VALIDITY_DATE, TIMESTAMP, medicalPrescription.getValidityDate(),
                PRIORITY, medicalPrescription.getPriority(),
                WHERE, ID, medicalPrescription.getId());

        int changedRowsNum = executeUpdate(query);

        disconnect();
        return changedRowsNum;
    }

    @Override
    public int deleteMedicalPrescription(long id) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s %s %s %s %s = %s",
                DELETE, FROM, MEDICAL_PRESCRIPTION, WHERE, ID, Long.toString(id));

        int changedRowsNum = executeUpdate(query);

        disconnect();
        return changedRowsNum;
    }

    @Override
    public List<MedicalPrescription> getAllMedicalPrescriptions() throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s * %s %s",
                SELECT, FROM, MEDICAL_PRESCRIPTION);

        ResultSet resultSet = executeQuery(query);

        List<MedicalPrescription> medicalPrescriptions = new LinkedList<MedicalPrescription>();
        MedicalPrescription medicalPrescription;
        while (resultSet.next()) {
            medicalPrescription = new MedicalPrescription(
                    resultSet.getLong(ID),
                    resultSet.getString(DESCRIPTION),
                    resultSet.getLong(PATIENT_ID),
                    resultSet.getLong(DOCTOR_ID),
                    resultSet.getTimestamp(CREATION_DATE),
                    resultSet.getTimestamp(VALIDITY_DATE),
                    resultSet.getByte(PRIORITY));

            medicalPrescriptions.add(medicalPrescription);
        }

        disconnect();
        return medicalPrescriptions;
    }

    @Override
    public List<MedicalPrescription> getMedicalPrescriptionsByFilter(String description, long patientId, byte priority) throws SQLException, ClassNotFoundException {
        connect();

        final String query = String.format("%s * %s %s " +
                        "%s %s %s %s %s " +
                        "%s = %s %s " +
                        "%s = %s;",
                SELECT, FROM, MEDICAL_PRESCRIPTION,
                WHERE, DESCRIPTION, LIKE, '%' + description + '%', AND,
                PATIENT_ID, patientId, AND,
                PRIORITY, priority);

        ResultSet resultSet = executeQuery(query);

        List<MedicalPrescription> medicalPrescriptions = new LinkedList<MedicalPrescription>();
        MedicalPrescription medicalPrescription;
        while (resultSet.next()) {
            medicalPrescription = new MedicalPrescription(
                    resultSet.getLong(ID),
                    resultSet.getString(DESCRIPTION),
                    resultSet.getLong(PATIENT_ID),
                    resultSet.getLong(DOCTOR_ID),
                    resultSet.getTimestamp(CREATION_DATE),
                    resultSet.getTimestamp(VALIDITY_DATE),
                    resultSet.getByte(PRIORITY));

            medicalPrescriptions.add(medicalPrescription);
        }

        disconnect();
        return medicalPrescriptions;
    }
}
