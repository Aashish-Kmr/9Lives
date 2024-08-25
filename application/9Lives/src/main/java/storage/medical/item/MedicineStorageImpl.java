package storage.medical.item;

import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicineStorageImpl implements MedicalItemStorage {

    private static final Statement dbStatement;
    private static MedicineStorageImpl medicineStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MedicineStorageImpl getInstance() {
        if (medicineStorage == null)
            medicineStorage = new MedicineStorageImpl();
        return medicineStorage;
    }

    @Override
    public void showAllItems() throws SQLException {
        String query = "SELECT * FROM medicine;";
        ResultSet resultSet = dbStatement.executeQuery(query);
        if (resultSet.next()) {
            System.out.println("Available Medicines:");
            do {
                System.out.printf("id: %s, name: %s, for illness: %s, cost: %s\n",
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("for_illness"),
                        resultSet.getString("cost"));
            } while (resultSet.next());
        } else
            System.out.println("No medicines available!");
    }
}
