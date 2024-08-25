package storage.person;

import entity.person.Person;
import entity.person.User;

import java.sql.SQLException;

public interface UserStorage {

    static UserStorage getInstance() {
        return UserStorageImpl.getInstance();
    }

    User getUser(Person person) throws SQLException;
}
