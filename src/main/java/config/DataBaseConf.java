package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConf implements DataBase {
    private final String user = "postgres";
    private final String password = "postgres";
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final Connection connection;

    public DataBaseConf() {
        this.connection = createConnection();
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getDBConnector() {
        return this.connection;
    }
}
