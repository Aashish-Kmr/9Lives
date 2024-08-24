package entity.medical.records;

public class Record {
    private final int id;
    private final int patientId;
    private final int doctorId;

    public Record(int id, int patientId, int doctorId) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    protected int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }
}
