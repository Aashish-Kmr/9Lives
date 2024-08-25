package storage.medical.record;

import entity.medical.records.Record;
import entity.medical.records.TestRecord;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestRecordStorageImpl implements RecordStorage {

    private static final Statement dbStatement;
    private static TestRecordStorageImpl testRecordStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TestRecordStorageImpl getInstance() {
        if (testRecordStorage == null)
            testRecordStorage = new TestRecordStorageImpl();
        return testRecordStorage;
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT * FROM test_record ORDER BY id DESC LIMIT 1;";
        ResultSet resultSet = dbStatement.executeQuery(query);
        resultSet.next();
        String answer = resultSet.getString("id");
        if (answer != null)
            return Integer.parseInt(answer) + 1;
        else
            return 1;
    }

    @Override
    public void addRecord(Record record) throws SQLException {
        TestRecord testRecord = (TestRecord) record;
        String query = String.format("INSERT INTO test_record VALUES( %d, %d, %d, %d)",
                testRecord.getId(),
                testRecord.getDoctorId(),
                testRecord.getPatientId(),
                testRecord.getTestId());
        dbStatement.execute(query);
        if (checkRecordId(testRecord.getId()))
            System.out.println("Medical record added successfully!");
        else
            System.out.println("Failed to add medical record.");
    }

    @Override
    public boolean checkRecordId(int recordId) throws SQLException {
        String query = "SELECT * FROM test_record WHERE id = " + recordId + ";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        return resultSet.next();
    }
}
