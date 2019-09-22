package ru.j4jdraft.vacparser;

import java.util.List;

public interface Storage {
    Vacancy add(Vacancy vacancy);

    Vacancy add(List<Vacancy> vacancies);

    List<Vacancy> findAll();

    Vacancy findByName(String name);

    Vacancy findLast();
}
