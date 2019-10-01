package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.j4jdraft.vacparser.model.ForumPage;
import ru.j4jdraft.vacparser.model.Vacancy;
import ru.j4jdraft.vacparser.parsers.ForumPageParser;
import ru.j4jdraft.vacparser.storage.Storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

// todo: write tests
/**
 * Получает из страницы вакансии,
 * обрабатывает их используя переданные предикаты.
 * Отправляет вакансии в хранилище.
 */
public class ForumPageProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(ForumPageProcessor.class);
    private static final int SKIP_ROWS = 4;

    private Storage storage;
    private Predicate<Vacancy> skipByTime;
    private Predicate<String> passByName;
    private Consumer<Vacancy> vacancyLoader;
    private ForumPageParser pageParser;

    public ForumPageProcessor(Storage storage, ForumPageParser pageParser, Predicate<String> passByName,
                              Predicate<Vacancy> skipByTime, Consumer<Vacancy> vacancyLoader) {
        this.storage = storage;
        this.pageParser = pageParser;
        this.passByName = passByName;
        this.skipByTime = skipByTime;
        this.vacancyLoader = vacancyLoader;
    }

    /**
     * Сохраняет вакансии с переданной страницы и возвращает адрес следующей страницы.
     * @param forumPageDoc
     * @return
     */
    public Optional<String> process(Document forumPageDoc) throws SQLException {

        /* Getting vacancies */
        ForumPage forumPage = pageParser.parse(forumPageDoc, SKIP_ROWS);
        List<Vacancy> allVacancies = forumPage.getVacancies();

        /* Filtering vacancies */
        boolean finishProcessing = false;
        List<Vacancy> filtered = new ArrayList<>();
        for (Vacancy vacancy : allVacancies) {
            if (skipByTime.test(vacancy)) {
                finishProcessing = true;
            } else if (passByName.test(vacancy.getName()) && storage.findByName(vacancy.getName()) == null) {
                filtered.add(vacancy);
            }
        }

        /* Filling vacancies */
        for (Vacancy vacancy : filtered) {
            vacancyLoader.accept(vacancy);
        }

        /* Storing vacancies */
        storage.addAll(filtered);
        if (finishProcessing) {
            LOG.info("Finishing processing");
            return Optional.empty();
        } else {
            String nextPage = forumPage.getNextPage();
            LOG.info("Next page to process: {}", nextPage);
            return Optional.of(nextPage);
        }
    }
}
