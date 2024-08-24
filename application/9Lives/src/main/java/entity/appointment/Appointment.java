package entity.appointment;

import java.time.LocalDateTime;

public class Appointment {
    private final int appointmentId;
    private final int patientId;
    private final int doctorId;
    private Status status;
    private LocalDateTime dateTime;

    public Appointment(Status status, int appointmentId, int patientId, int doctorId, LocalDateTime dateTime) {
        this.status = status;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public enum Status {
        BOOKED,
        CANCELLED
    }
}
