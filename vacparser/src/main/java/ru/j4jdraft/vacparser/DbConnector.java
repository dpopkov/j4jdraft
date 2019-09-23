package ru.j4jdraft.vacparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private final AppSettings settings;

    public DbConnector(AppSettings settings) {
        this.settings = settings;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(settings.jdbcUrl(), settings.jdbcUser(), settings.jdbcPassword());
    }
}
