package config;

import java.sql.Connection;

public interface DataBase {
    Connection getDBConnector();
}
