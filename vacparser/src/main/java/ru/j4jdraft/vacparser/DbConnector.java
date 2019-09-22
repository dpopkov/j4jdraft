package ru.j4jdraft.vacparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private final String url;
    private final String user;
    private final String password;

    public DbConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
