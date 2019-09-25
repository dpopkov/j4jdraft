package ru.j4jdraft.vacparser;

import java.sql.Connection;
import java.util.List;

// todo: write tests and implement
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
    public void addAll(List<Vacancy> vacancies) {

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
