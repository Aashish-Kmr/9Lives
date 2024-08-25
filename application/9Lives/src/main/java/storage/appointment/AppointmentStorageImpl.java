package storage.appointment;

import entity.appointment.Appointment;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentStorageImpl implements AppointmentStorage {
    private static final Statement dbStatement;
    private static AppointmentStorageImpl appointmentStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static AppointmentStorageImpl getInstance() {
        if (appointmentStorage == null) {
            appointmentStorage = new AppointmentStorageImpl();
        }
        return appointmentStorage;
    }

    public void showAppointments(int doctorId) {
        String query = "SELECT * FROM appointment WHERE doctor_id = " + doctorId + ";";
        try {
            ResultSet resultSet = dbStatement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("Current appointments for doctor with doctor id: " + doctorId);
                do {
                    String patient_id = resultSet.getString("patient_id");
                    String datetime = resultSet.getString("datetime");
                    String status = resultSet.getString("status");
                    System.out.printf("patient id: %s, date & time: %s, status: %s\n", patient_id, datetime, status);
                } while (resultSet.next());
            } else {
                System.out.println("No appointments currently booked for doctor id: " + doctorId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNextId() throws SQLException {
        String query = "SELECT * FROM appointment ORDER BY appointment_id DESC LIMIT 1;";
        ResultSet resultSet = dbStatement.executeQuery(query);
        resultSet.next();
        String answer = resultSet.getString("appointment_id");
        if (answer != null)
            return Integer.parseInt(answer) + 1;
        else
            return 1;
    }

    public void addAppointment(Appointment appointment) throws SQLException {
        String query = String.format("INSERT INTO appointment VALUES(%d, \"%d\", \"%d\", \"%s\", \"%s\");",
                appointment.getAppointmentId(),
                appointment.getDoctorId(),
                appointment.getPatientId(),
                appointment.getDatetime(),
                appointment.getStatus().name);
        dbStatement.execute(query);
        if (checkAppointmentId(appointment.getAppointmentId())) {
            System.out.println("Appointment booked successfully");
        } else {
            System.out.println("Failed to book appointment");
        }
    }

    private boolean checkAppointmentId(int id) throws SQLException {
        String query = "SELECT * FROM appointment WHERE appointment_id = " + id + ";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        return resultSet.next();
    }

    @Override
    public void cancelAppointment(int appointmentId) throws SQLException {
        String query = String.format("UPDATE appointment SET status = \"%s\" WHERE appointment_id = %d;",
                Appointment.Status.CANCELLED.name,
                appointmentId);
        dbStatement.execute(query);
    }

    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        String query = "DELETE FROM appointment WHERE appointment_id = " + appointmentId + ";";
        dbStatement.execute(query);
    }
}
