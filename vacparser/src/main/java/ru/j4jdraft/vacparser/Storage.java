package ru.j4jdraft.vacparser;

import java.util.List;

public interface Storage {
    Vacancy add(Vacancy vacancy);

    void addAll(List<Vacancy> vacancies);

    List<Vacancy> findAll();

    Vacancy findByName(String name);

    Vacancy findLast();
}
