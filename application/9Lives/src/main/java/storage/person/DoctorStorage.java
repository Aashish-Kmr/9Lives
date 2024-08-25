package storage.person;

import entity.person.Doctor;
import entity.person.Person;

import java.sql.SQLException;

public interface DoctorStorage {
    static DoctorStorage getInstance() {
        return DoctorStorageImpl.getInstance();
    }

    int getNextId() throws SQLException;

    Doctor getDoctor(int doctorId) throws SQLException;

    Doctor getDoctor(Person person) throws SQLException;

    void addDoctor(Doctor doctor) throws SQLException;

    void updateFirstName(int doctorId, String firstName) throws SQLException;

    void updateLastName(int doctorId, String lastName) throws SQLException;

    void updateSpecialization(int doctorId, String specialization) throws SQLException;

    void removeDoctor(int doctorId) throws SQLException;

    void showDoctorDetails(int doctorId);

    void showAllDoctors();
}
