package storage.medical.item;

import java.sql.SQLException;

public interface MedicalItemStorage {

    static MedicalItemStorage getMedicineInstance() {
        return MedicineStorageImpl.getInstance();
    }

    static MedicalItemStorage getMedicalTestInstance() {
        return MedicalTestStorageImpl.getInstance();
    }

    void showAllItems() throws SQLException;
}
