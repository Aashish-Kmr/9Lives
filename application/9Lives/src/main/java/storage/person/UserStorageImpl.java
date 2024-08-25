package storage.person;

import entity.person.Person;
import entity.person.User;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserStorageImpl implements UserStorage {

    private static final Statement dbStatement;
    private static UserStorageImpl userStorage;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserStorageImpl getInstance() {
        if (userStorage == null)
            userStorage = new UserStorageImpl();
        return userStorage;
    }

    @Override
    public User getUser(Person person) throws SQLException {
        String query = "SELECT * FROM user WHERE username = \"" + person.getUsername() + "\";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        resultSet.next();
        int id = Integer.parseInt(resultSet.getString("user_id"));
        String phone = resultSet.getString("phone_num");
        return new User(id, phone);
    }
}
