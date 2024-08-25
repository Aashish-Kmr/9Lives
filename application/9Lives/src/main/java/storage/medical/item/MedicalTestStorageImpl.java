package storage.medical.item;

import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicalTestStorageImpl implements MedicalItemStorage {

    private static final Statement dbStatement;
    private static MedicalTestStorageImpl medicalTestStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MedicalTestStorageImpl getInstance() {
        if (medicalTestStorage == null)
            medicalTestStorage = new MedicalTestStorageImpl();
        return medicalTestStorage;
    }

    @Override
    public void showAllItems() throws SQLException {
        String query = "SELECT * FROM medical_test;";
        ResultSet resultSet = dbStatement.executeQuery(query);
        if (resultSet.next()) {
            System.out.println("Available Tests:");
            do {
                System.out.printf("id: %s, name: %s, for illness: %s, cost: %s\n",
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("for_illness"),
                        resultSet.getString("cost"));
            } while (resultSet.next());
        } else
            System.out.println("No tests available!");
    }
}
