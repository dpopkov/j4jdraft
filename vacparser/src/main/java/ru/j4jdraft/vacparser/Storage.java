package ru.j4jdraft.vacparser;

import java.sql.SQLException;
import java.util.List;

public interface Storage {
    Vacancy add(Vacancy vacancy) throws SQLException;

    void addAll(List<Vacancy> vacancies) throws SQLException;

    List<Vacancy> findAll() throws SQLException;

    Vacancy findByName(String name) throws SQLException;

    Vacancy findById(int id) throws SQLException;

    Vacancy findLast() throws SQLException;
}
