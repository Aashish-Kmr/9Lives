package storage.person;

import entity.person.Admin;
import entity.person.Person;
import storage.SQLiteDatabaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminStorageImpl implements AdminStorage {

    private static final Statement dbStatement;
    private static AdminStorageImpl adminStorage = null;

    static {
        try {
            dbStatement = SQLiteDatabaseStorage.getStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static AdminStorage getInstance() {
        if (adminStorage == null) {
            adminStorage = new AdminStorageImpl();
        }
        return adminStorage;
    }

    @Override
    public Admin getAdmin(Person person) throws SQLException {
        String query = "SELECT * FROM admin WHERE username = \"" + person.getUsername() + "\";";
        ResultSet resultSet = dbStatement.executeQuery(query);
        resultSet.next();
        int id = Integer.parseInt(resultSet.getString("admin_id"));
        String name = resultSet.getString("name");
        return new Admin(id, name);
    }
}
