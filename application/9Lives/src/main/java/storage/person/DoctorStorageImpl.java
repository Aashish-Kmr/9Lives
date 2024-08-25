package storage.person;

import entity.person.Doctor;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorStorageImpl implements DoctorStorage {

    private static final Statement dbStatement;
    private static DoctorStorageImpl doctorStorage;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DoctorStorage getInstance() {
        if (doctorStorage == null)
            doctorStorage = new DoctorStorageImpl();
        return doctorStorage;
    }

    @Override
    public void showAllDoctors() {
        String query = "SELECT * FROM doctor;";
        try {
            ResultSet resultSet = dbStatement.executeQuery(query);
            while (resultSet.next()) {
                System.out.printf("id: %d, name: %s, specialization: %s\n",
                        Integer.parseInt(resultSet.getString("doctor_id")),
                        resultSet.getString("firstname") + resultSet.getString("lastname"),
                        resultSet.getString("specialization"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Doctor getDoctor(int doctorId) throws SQLException {
        String query = "SELECT * FROM doctor WHERE doctor_id = " + doctorId + ";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        if (resultSet.next())
            return new Doctor(
                    doctorId,
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("specialization"),
                    resultSet.getString("username"));
        else
            return null;
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT * FROM doctor ORDER BY doctor_id DESC LIMIT 1;";
        ResultSet resultSet = dbStatement.executeQuery(query);
        resultSet.next();
        String answer = resultSet.getString("doctor_id");
        if (answer != null)
            return Integer.parseInt(answer) + 1;
        else
            return 1;
    }

    @Override
    public void addDoctor(Doctor doctor) throws SQLException {
        String query = String.format("INSERT INTO doctor VALUES(%d, \"%s\", \"%s\", \"%s\", \"%s\");",
                doctor.getDoctorId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpecialization(),
                doctor.getUsername());
        dbStatement.execute(query);
        if (getDoctor(doctor.getDoctorId()) != null) {
            System.out.println("Doctor added successfully");
        } else {
            System.out.println("Failed to add doctor");
        }
    }

    @Override
    public void showDoctorDetails(int doctorId) {
        try {
            System.out.println(getDoctor(doctorId));
        } catch (SQLException e) {
            System.out.println("Error fetching doctor with doctor id: " + doctorId);
        }
    }

    @Override
    public void updateFirstName(int id, String firstName) throws SQLException {
        String query = String.format("UPDATE doctor SET firstname = \"%s\" WHERE doctor_id = %d;",
                firstName,
                id);
        dbStatement.execute(query);
    }

    @Override
    public void updateLastName(int id, String lastName) throws SQLException {
        String query = String.format("UPDATE doctor SET lastname = \"%s\" WHERE doctor_id = %d;",
                lastName,
                id);
        dbStatement.execute(query);
    }

    @Override
    public void updateSpecialization(int id, String specialization) throws SQLException {
        String query = String.format("UPDATE doctor SET specialization = \"%s\" WHERE doctor_id = %d;",
                specialization,
                id);
        dbStatement.execute(query);
    }

    @Override
    public void removeDoctor(int doctorId) throws SQLException {
        String query = "DELETE FROM doctor WHERE doctor_id = " + doctorId + ";";
        dbStatement.execute(query);
    }

}
