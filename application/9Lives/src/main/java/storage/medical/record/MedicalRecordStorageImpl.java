package storage.medical.record;

import entity.medical.records.MedicalRecord;
import entity.medical.records.Record;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicalRecordStorageImpl implements RecordStorage {

    private static final Statement dbStatement;
    private static MedicalRecordStorageImpl medicalRecordStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MedicalRecordStorageImpl getInstance() {
        if (medicalRecordStorage == null)
            medicalRecordStorage = new MedicalRecordStorageImpl();
        return medicalRecordStorage;
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT * FROM medical_record ORDER BY id DESC LIMIT 1;";
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
        MedicalRecord medicalRecord = (MedicalRecord) record;
        String query = String.format("INSERT INTO medical_record VALUES( %d, %d, %d, %d)",
                medicalRecord.getId(),
                medicalRecord.getDoctorId(),
                medicalRecord.getPatientId(),
                medicalRecord.getMedicineId());
        dbStatement.execute(query);
        if (checkRecordId(medicalRecord.getId()))
            System.out.println("Medical record added successfully!");
        else
            System.out.println("Failed to add medical record.");
    }

    @Override
    public boolean checkRecordId(int recordId) throws SQLException {
        String query = "SELECT * FROM medical_record WHERE id = " + recordId + ";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        return resultSet.next();
    }
}
