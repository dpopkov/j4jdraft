package ru.j4jdraft.vacparser.research;

import ru.j4jdraft.vacparser.AppSettings;
import ru.j4jdraft.vacparser.storage.DbHelper;
import ru.j4jdraft.vacparser.model.Vacancy;

import java.sql.*;
import java.time.LocalDateTime;

import static ru.j4jdraft.vacparser.storage.DbStorage.ADD_VACANCY;

public class InsertingVacancy {
    private static final AppSettings CONFIG = new AppSettings("test_vacparser_01_app.properties");
    private static final LocalDateTime NOW = LocalDateTime.now();

    public static void main(String[] args) {
        try (Connection conn = DbHelper.getConnection(CONFIG)) {
            Vacancy vacancy = new Vacancy("Bob2", "Java trainee", "example.com", NOW, NOW);
            PreparedStatement stmt = conn.prepareStatement(ADD_VACANCY, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, vacancy.getName());
            stmt.setString(2, vacancy.getDescription());
            stmt.setString(3, vacancy.getLink());
            stmt.setTimestamp(4, Timestamp.valueOf(vacancy.getCreated()));
            stmt.setTimestamp(5, Timestamp.valueOf(vacancy.getModified()));
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                vacancy.setId(id);
                System.out.println("Inserted with id = " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
