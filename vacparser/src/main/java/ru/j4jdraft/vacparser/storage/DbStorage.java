package ru.j4jdraft.vacparser.storage;

import ru.j4jdraft.vacparser.model.Vacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@SuppressWarnings("SqlResolve")
public class DbStorage implements Storage {
    public static final String ADD_VACANCY =
            "insert into vacancy (name, description, link, created, modified) values (?, ?, ?, ?, ?)";
    public static final String SELECT_VACANCY = "select id, name, description, link, created, modified from vacancy";
    public static final String FIND_BY_ID = SELECT_VACANCY + " where id = ?";
    public static final String FIND_BY_NAME = SELECT_VACANCY + " where name = ?";
    public static final String SELECT_ALL = SELECT_VACANCY;
    public static final String FIND_LAST = SELECT_VACANCY + " order by modified desc limit 1";

    private final Connection connection;

    public DbStorage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Vacancy add(Vacancy vacancy) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_VACANCY, Statement.RETURN_GENERATED_KEYS)) {
            setVacancyFields(stmt, vacancy);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                vacancy.setId(id);
                return vacancy;
            }
        }
        return null;
    }

    @Override
    public void addAll(List<Vacancy> vacancies) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement stmt = connection.prepareStatement(ADD_VACANCY, Statement.RETURN_GENERATED_KEYS)) {
            for (Vacancy vacancy : vacancies) {
                setVacancyFields(stmt, vacancy);
                stmt.addBatch();
            }
            stmt.executeBatch();
            ResultSet keys = stmt.getGeneratedKeys();
            for (Vacancy vacancy : vacancies) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    vacancy.setId(id);
                }
            }
            connection.commit();
        }
    }

    @Override
    public List<Vacancy> findAll() throws SQLException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet result = stmt.executeQuery(SELECT_ALL);
            while (result.next()) {
                Vacancy vacancy = getCurrentVacancy(result);
                vacancies.add(vacancy);
            }
        }
        return vacancies;
    }

    @Override
    public Vacancy findByName(String name) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            return getVacancy(result);
        }
    }

    @Override
    public Vacancy findById(int id) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            return getVacancy(result);
        }
    }

    @Override
    public Vacancy findLast() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            ResultSet result = stmt.executeQuery(FIND_LAST);
            return getVacancy(result);
        }
    }

    private void setVacancyFields(PreparedStatement stmt, Vacancy vacancy) throws SQLException {
        stmt.setString(1, vacancy.getName());
        stmt.setString(2, vacancy.getDescription());
        stmt.setString(3, vacancy.getLink());
        stmt.setTimestamp(4, Timestamp.valueOf(vacancy.getCreated()));
        stmt.setTimestamp(5, Timestamp.valueOf(vacancy.getModified()));
    }

    private Vacancy getVacancy(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return getCurrentVacancy(rs);
        }
        return null;
    }

    private Vacancy getCurrentVacancy(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String link = rs.getString("link");
        Timestamp created = rs.getTimestamp("created");
        Timestamp modified = rs.getTimestamp("modified");
        Vacancy vacancy = new Vacancy(name, description, link, created.toLocalDateTime(), modified.toLocalDateTime());
        vacancy.setId(id);
        return vacancy;
    }
}
