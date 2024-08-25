package storage.medical.record;

import entity.medical.records.Record;

import java.sql.SQLException;

public interface RecordStorage {

    static RecordStorage getMedicalRecordInstance() {
        return MedicalRecordStorageImpl.getInstance();
    }

    static RecordStorage getTestRecordInstance() {
        return TestRecordStorageImpl.getInstance();
    }

    int getNextId() throws SQLException;

    void addRecord(Record record) throws SQLException;

    boolean checkRecordId(int recordId) throws SQLException;
}
