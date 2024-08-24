package entity.medical.records;

public class TestRecord extends Record {
    private final int testId;

    public TestRecord(int id, int patientId, int doctorId, int testId) {
        super(id, patientId, doctorId);
        this.testId = testId;
    }

    public int getTestId() {
        return testId;
    }
}
