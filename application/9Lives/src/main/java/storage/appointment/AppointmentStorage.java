package storage.appointment;

import entity.appointment.Appointment;

import java.sql.SQLException;

public interface AppointmentStorage {

    static AppointmentStorage getInstance() {
        return AppointmentStorageImpl.getInstance();
    }

    void cancelAppointment(int appointmentId) throws SQLException;

    void deleteAppointment(int appointmentId) throws SQLException;

    void showAppointments(int doctorId);

    int getNextId() throws SQLException;

    void addAppointment(Appointment appointment) throws SQLException;
}
