package entity.medical.records;

public class MedicalRecord extends Record {
    private final int medicineId;

    public MedicalRecord(int id, int patientId, int doctorId, int medicineId) {
        super(id, patientId, doctorId);
        this.medicineId = medicineId;
    }

    public int getMedicineId() {
        return medicineId;
    }
}
