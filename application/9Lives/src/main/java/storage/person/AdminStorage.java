package storage.person;

import entity.person.Admin;
import entity.person.Person;

import java.sql.SQLException;

public interface AdminStorage {

    static AdminStorage getInstance() {
        return AdminStorageImpl.getInstance();
    }

    Admin getAdmin(Person username) throws SQLException;
}
