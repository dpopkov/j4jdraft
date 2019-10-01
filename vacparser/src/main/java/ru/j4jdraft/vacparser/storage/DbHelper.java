package ru.j4jdraft.vacparser.storage;

import ru.j4jdraft.vacparser.AppSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    public static Connection getConnection(AppSettings config) throws SQLException {
        return DriverManager.getConnection(config.jdbcUrl(), config.jdbcUser(), config.jdbcPassword());
    }
}
