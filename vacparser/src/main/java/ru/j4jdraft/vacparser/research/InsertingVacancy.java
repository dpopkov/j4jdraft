package ru.j4jdraft.vacparser.research;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.j4jdraft.TmpResearch;
import ru.j4jdraft.vacparser.AppSettings;
import ru.j4jdraft.vacparser.DbHelper;
import ru.j4jdraft.vacparser.Vacancy;

import java.sql.*;
import java.time.LocalDateTime;

import static ru.j4jdraft.vacparser.DbStorage.ADD_VACANCY;

public class InsertingVacancy {
    private static final Logger LOG = LoggerFactory.getLogger(TmpResearch.class);
    private static final AppSettings CONFIG = new AppSettings("test_vacparser_01_app.properties");
    private static final LocalDateTime NOW = LocalDateTime.now();

    // todo: check how to insert vacancy

    public static void main(String[] args) {
        try (Connection conn = DbHelper.getConnection(CONFIG)) {
            Vacancy vacancy = new Vacancy("Bob2", "Java trainee", "example.com", NOW);
            PreparedStatement stmt = conn.prepareStatement(ADD_VACANCY, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, vacancy.getName());
            stmt.setString(2, vacancy.getDescription());
            stmt.setString(3, vacancy.getLink());
            stmt.setTimestamp(4, Timestamp.valueOf(vacancy.getCreated()));
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

    // todo: check how to insert list of vacancies and get IDs

}
