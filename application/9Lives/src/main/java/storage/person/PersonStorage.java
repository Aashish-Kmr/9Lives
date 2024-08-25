package storage.person;

import entity.person.Person;

import java.sql.SQLException;

public interface PersonStorage {

    static PersonStorage getInstance() {
        return PersonStorageImpl.getInstance();
    }

    Person getPerson(String username) throws SQLException;

    void addPerson(Person person) throws SQLException;

    boolean checkPersonUsername(String username) throws SQLException;
}
