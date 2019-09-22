package ru.j4jdraft.vacparser;

import java.sql.Connection;
import java.util.List;

public class DbStorage implements Storage {
    private Connection connection;

    public DbStorage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Vacancy add(Vacancy vacancy) {
        return null;
    }

    @Override
    public Vacancy add(List<Vacancy> vacancies) {
        return null;
    }

    @Override
    public List<Vacancy> findAll() {
        return null;
    }

    @Override
    public Vacancy findByName(String name) {
        return null;
    }

    @Override
    public Vacancy findLast() {
        return null;
    }
}
