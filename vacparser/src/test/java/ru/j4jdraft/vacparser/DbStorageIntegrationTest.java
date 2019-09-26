package ru.j4jdraft.vacparser;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DbStorageIntegrationTest {
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
    private static final AppSettings CONFIG = new AppSettings("test_vacparser_app.properties");

    @Test
    public void whenAddThenCanFindById() throws SQLException {
        try (Connection connection = ConnectionRollback.create(DbHelper.getConnection(CONFIG))) {
            Storage storage = new DbStorage(connection);
            Vacancy vacancy = new Vacancy("Java trainee", "Skills: Java", "example.com", DATE_TIME);
            assertNull(vacancy.getId());
            vacancy = storage.add(vacancy);
            assertNotNull(vacancy.getId());
            Vacancy found = storage.findById(vacancy.getId());
            assertThat(found.getId(), is(vacancy.getId()));
            assertThat(found, is(vacancy));
        }
    }

    @Test
    public void whenAddThenCanFindByName() throws SQLException {
        try (Connection connection = ConnectionRollback.create(DbHelper.getConnection(CONFIG))) {
            Storage storage = new DbStorage(connection);
            Vacancy vacancy = new Vacancy("Java trainee", "Skills: Java", "link", DATE_TIME);
            vacancy = storage.add(vacancy);
            Vacancy found = storage.findByName("Java trainee");
            assertThat(found, is(vacancy));
        }
    }

    @Test
    public void whenAddAllThenAllCanBeFound() throws SQLException {
        try (Connection connection = ConnectionRollback.create(DbHelper.getConnection(CONFIG))) {
            Storage storage = new DbStorage(connection);
            Vacancy vacancy1 = new Vacancy("Java trainee 1", "Skills: Java", "link", DATE_TIME);
            Vacancy vacancy2 = new Vacancy("Java trainee 2", "Skills: Java", "link", DATE_TIME);
            List<Vacancy> vacancies = List.of(vacancy1, vacancy2);
            storage.addAll(vacancies);
            List<Vacancy> found = storage.findAll();
            assertThat(found, Matchers.hasItems(vacancy1, vacancy2));
        }
    }
}
