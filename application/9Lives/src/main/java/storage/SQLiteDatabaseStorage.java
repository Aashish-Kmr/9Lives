package storage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseStorage {
    private static Connection connection = null;

    private static Connection getConnection() throws SQLException {
        if (connection == null) {
            URL url = SQLiteDatabaseStorage.class.getClassLoader().getResource("9lives.db");
            assert url != null;
            connection = DriverManager.getConnection("jdbc:sqlite:" + url.getPath());
        }
        return connection;
    }

    public static Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }
}
