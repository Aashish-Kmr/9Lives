package storage.person;

import entity.person.Patient;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientStorageImpl implements PatientStorage {

    private static final Statement dbStatement;
    private static PatientStorageImpl patientStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PatientStorageImpl getInstance() {
        if (patientStorage == null)
            patientStorage = new PatientStorageImpl();
        return patientStorage;
    }

    @Override
    public void addPatient(Patient patient) throws SQLException {
        String query = String.format("INSERT INTO patient VALUES(%d, \"%s\", \"%s\", \"%s\", %d);",
                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getMedicalCondition(),
                patient.getUserId());
        dbStatement.execute(query);
        if (checkPatientId(patient.getPatientId())) {
            System.out.println("Patient added successfully");
        } else {
            System.out.println("Failed to add patient");
        }
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT * FROM patient ORDER BY patient_id DESC LIMIT 1;";
        ResultSet resultSet = dbStatement.executeQuery(query);
        resultSet.next();
        String answer = resultSet.getString("patient_id");
        if (answer != null)
            return Integer.parseInt(answer) + 1;
        else
            return 1;
    }

    @Override
    public boolean checkPatientId(int id) throws SQLException {
        String query = "SELECT * FROM patient WHERE patient_id = " + id + ";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        return resultSet.next();
    }

    @Override
    public void showAllPatients() {
        String query = "SELECT * FROM patient;";
        try {
            ResultSet resultSet = dbStatement.executeQuery(query);
            while (resultSet.next()) {
                System.out.printf("id: %d, name: %s, medical condition: %s\n",
                        Integer.parseInt(resultSet.getString("patient_id")),
                        resultSet.getString("firstname") + resultSet.getString("lastname"),
                        resultSet.getString("medical_condition"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
