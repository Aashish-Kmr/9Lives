package storage.person;

import entity.person.Person;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonStorageImpl implements PersonStorage {

    private static final Statement dbStatement;
    private static PersonStorageImpl personStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PersonStorageImpl getInstance() {
        if (personStorage == null)
            personStorage = new PersonStorageImpl();
        return personStorage;
    }

    @Override
    public Person getPerson(String username) throws SQLException {
        String query = "SELECT * FROM person WHERE username = \"" + username + "\";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        resultSet.next();
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String role = resultSet.getString("role");
        return new Person(username, password, email, role);
    }

    @Override
    public void addPerson(Person person) throws SQLException {
        String query = String.format("INSERT INTO person VALUES(\"%S\", \"%S\", \"%S\", \"%S\");",
                person.getUsername(),
                person.getPassword(),
                person.getEmail(),
                person.getRole());
        dbStatement.execute(query);
        if (checkPersonUsername(person.getUsername())) {
            System.out.println("Person added successfully!");
        } else {
            System.out.println("Failed to add person.");
        }
    }

    @Override
    public boolean checkPersonUsername(String username) throws SQLException {
        String query = "SELECT * FROM person WHERE username = \"" + username + "\";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        return resultSet.next();
    }
}
