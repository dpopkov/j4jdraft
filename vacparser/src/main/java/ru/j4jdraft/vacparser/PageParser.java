package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;

import java.util.List;

// todo: write tests and implement
public class PageParser {
    private Document page;

    public PageParser(Document page) {
        this.page = page;
    }

    public List<Vacancy> vacancies() {
        return null;
    }

    public String nextPageUrl() {
        return null;
    }
}
