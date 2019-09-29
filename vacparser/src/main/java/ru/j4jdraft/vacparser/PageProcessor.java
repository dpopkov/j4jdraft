package ru.j4jdraft.vacparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

// todo: write tests
/**
 * Получает из страницы вакансии,
 * отфильтровывает их.
 * Отправляет вакансии в хранилище.
 */
public class PageProcessor {
    private static final int SKIP_ROWS = 4;

    private Storage storage;
    private Predicate<Vacancy> skipByTime;
    private Predicate<String> passByName;
    private VacancyPageParser vacancyPageParser = new VacancyPageParser();

    public PageProcessor(Storage storage, Predicate<String> passByName, Predicate<Vacancy> skipByTime) {
        this.storage = storage;
        this.passByName = passByName;
        this.skipByTime = skipByTime;
    }

    /**
     * Сохраняет вакансии с переданной страницы и возвращает адрес следующей страницы.
     * @param page
     * @return
     */
    public Optional<String> process(Document page) throws SQLException, IOException {
        ForumPageParser parser = new ForumPageParser(page);
        List<Vacancy> vacancies = parser.parse(SKIP_ROWS);
        boolean finishProcessing = false;
        List<Vacancy> filtered = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            if (skipByTime.test(vacancy)) {
                finishProcessing = true;
            } else if (passByName.test(vacancy.getName()) && storage.findByName(vacancy.getName()) == null) {
                filtered.add(vacancy);
            }
        }
        for (Vacancy vacancy : filtered) {
            Document vacancyDoc = Jsoup.connect(vacancy.getLink()).get();
            vacancyPageParser.parseAndFill(vacancyDoc, vacancy);
        }
        storage.addAll(filtered);
        return finishProcessing ? Optional.empty() : Optional.of(parser.nextPageUrl());
    }
}
