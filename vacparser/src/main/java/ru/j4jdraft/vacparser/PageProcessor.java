package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;

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
    private Storage storage;
    private Predicate<Vacancy> predicateByTime;

    public PageProcessor(Storage storage, Predicate<Vacancy> predicateByTime) {
        this.storage = storage;
        this.predicateByTime = predicateByTime;
    }

    /**
     * Сохраняет вакансии с переданной страницы и возвращает адрес следующей страницы.
     * @param page
     * @return
     */
    public Optional<String> process(Document page) {
        PageParser parser = new PageParser(page);
        List<Vacancy> vacancies = parser.vacancies();
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
