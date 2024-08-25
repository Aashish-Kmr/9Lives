package entity.appointment;

public class Appointment {
    private final int appointmentId;
    private final int patientId;
    private final int doctorId;
    private Status status;
    private String datetime;

    public Appointment(Status status, int appointmentId, int patientId, int doctorId, String datetime) {
        this.status = status;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.datetime = datetime;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public enum Status {
        BOOKED("Booked"),
        CANCELLED("Cancelled");

        public final String name;
        Status(String name){
            this.name = name;
        }
    }
}
