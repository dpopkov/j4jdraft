package ru.j4jdraft.vacparser.model;

import java.util.List;

/**
 * Result of forum page parsing which encapsulates vacancies and link to next forum page.
 */
public class ForumPage {
    private List<Vacancy> vacancies;
    private String nextPage;

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
