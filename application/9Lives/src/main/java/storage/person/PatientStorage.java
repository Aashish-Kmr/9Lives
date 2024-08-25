package storage.person;

import entity.person.Patient;

import java.sql.SQLException;

public interface PatientStorage {

    static PatientStorage getInstance() {
        return PatientStorageImpl.getInstance();
    }

    void showAllPatients();

    void addPatient(Patient patient) throws SQLException;

    int getNextId() throws SQLException;

    boolean checkPatientId(int id) throws SQLException;

}
