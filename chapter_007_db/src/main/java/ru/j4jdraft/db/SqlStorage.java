package ru.j4jdraft.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.j4jdraft.tracker.JdbcHelper;

import java.sql.*;
import java.util.Properties;

public class SqlStorage {
    private static final Logger log = LoggerFactory.getLogger(SqlStorage.class);
    private static final String PROPERTIES_FILENAME = "db.properties";
    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/carstorage";

    public static void main(String[] args) {
        Properties props = JdbcHelper.readProperties(PROPERTIES_FILENAME);
        try (Connection conn = DriverManager.getConnection(props.getProperty("url", DEFAULT_URL), props)) {
            select(conn);
            update(conn, "Liftback", 2);
            select(conn);
            update(conn, "Лифтбек", 2);
            select(conn);
            insert(conn, "Limousine3");
            selectAll(conn);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    private static void insert(Connection conn, String name) throws SQLException {
        String sql = "INSERT into body_type (name) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.executeUpdate();
        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            int id = keys.getInt(1);
            System.out.printf("Inserted row with id %d%n", id);
        } else {
            throw new SQLException("Could not get generated keys");
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void update(Connection conn, String name, int id) throws SQLException {
        String sql = "UPDATE body_type SET name = ? WHERE body_type_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, id);
        int rows = ps.executeUpdate();
        System.out.printf("%d rows updated%n", rows);
    }

    private static void select(Connection conn) throws SQLException {
        String sql = "SELECT body_type_id, name FROM body_type WHERE body_type_id IN(?, ?) ORDER BY body_type_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 2);
        ps.setInt(2, 4);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("body_type_id");
            String name = rs.getString("name");
            System.out.printf("%d | %s%n", id, name);
        }
    }

    private static void selectAll(Connection conn) throws SQLException {
        String sql = "SELECT body_type_id, name FROM body_type ORDER BY body_type_id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("body_type_id");
            String name = rs.getString("name");
            System.out.printf("%d | %s%n", id, name);
        }
    }
}
