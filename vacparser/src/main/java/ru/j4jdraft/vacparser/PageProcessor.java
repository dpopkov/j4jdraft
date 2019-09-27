package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

// todo: write tests
/**
 * Получает из страницы вакансии,
 * отфильтровывает их.
 * Сохраняет вакансии в БД.
 */
public class PageProcessor {
    private static final int SKIP_ROWS = 4;

    private Storage storage;
    // todo: change predicateByTime to LocalDateTime
    private Predicate<Vacancy> predicateByTime;
    // todo: add predicateByName to constructor
    private Predicate<String> predicateByName;

    public PageProcessor(Storage storage,  Predicate<Vacancy> predicateByTime) {
        this.storage = storage;
        this.predicateByTime = predicateByTime;
    }

    /**
     * Сохраняет вакансии с переданной страницы и возвращает адрес следующей страницы.
     * @param page
     * @return
     */
    public Optional<String> process(Document page) throws SQLException {
        ForumPageParser parser = new ForumPageParser(page);
        List<Vacancy> vacancies = parser.parse(SKIP_ROWS);

        // todo: filter for 'Java' using predicateByName and get data from vacancies pages

        boolean stop = false;
        List<Vacancy> filtered = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            if (predicateByTime.test(vacancy)) {
                stop = true;
            } else if (storage.findByName(vacancy.getName()) == null) {
                filtered.add(vacancy);
            }
        }
        storage.addAll(filtered);
        return stop ? Optional.empty() : Optional.of(parser.nextPageUrl());
    }
}
