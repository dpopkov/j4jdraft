package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.j4jdraft.vacparser.parsers.VacancyPageParser;
import ru.j4jdraft.vacparser.storage.DbHelper;
import ru.j4jdraft.vacparser.storage.DbStorage;
import ru.j4jdraft.vacparser.model.Vacancy;
import ru.j4jdraft.vacparser.parsers.ForumPageParser;
import ru.j4jdraft.vacparser.storage.Storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Содержит основную логику последовательной загрузки и обработки страниц.
 */
public class VacanciesScraper implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(VacanciesScraper.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        AppSettings settings = (AppSettings) context.getJobDetail().getJobDataMap().get("appSettings");
        Connection connection = null;
        try {
            connection = DbHelper.getConnection(settings);
            Storage storage = new DbStorage(connection);
            String pageUrl = settings.siteUrl();
            LocalDateTime temporalBoundary = calculateDateTimeLimit(storage);
            Predicate<Vacancy> skipByTime = vacancy -> !vacancy.getCreated().isAfter(temporalBoundary);
            Predicate<String> passByName = name -> name.contains("Java") && !name.toLowerCase().contains("script");
            DocumentLoader loader = new DocumentLoader();
            VacancyPageParser vacParser = new VacancyPageParser();
            ForumPageProcessor processor = new ForumPageProcessor(storage, new ForumPageParser(), passByName,
                    skipByTime, new VacancyContentLoader(loader, vacParser)
            );

            Optional<Document> loaded = loader.apply(pageUrl);
            if (loaded.isPresent()) {
                Document forumPage = loaded.get();
                Optional<String> parsedLink = processor.process(forumPage);
                while (parsedLink.isPresent()) {
                    pageUrl = parsedLink.get();
                    loaded = loader.apply(pageUrl);
                    if (loaded.isPresent()) {
                        forumPage = loaded.get();
                        parsedLink = processor.process(forumPage);
                    } else {
                        LOG.warn("Processing interrupted because could not load page: " + pageUrl);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Database error", e);
        }  finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("Error Closing connection", e);
                }
            }
        }
    }

    private static LocalDateTime calculateDateTimeLimit(Storage storage) throws SQLException {
        LocalDateTime temporalBoundary;
        Vacancy lastVacancy = storage.findLast();
        if (lastVacancy != null) {
            temporalBoundary = lastVacancy.getCreated();
        } else {
            int nextYear = LocalDate.now().plusYears(1L).getYear();
            temporalBoundary = LocalDateTime.of(nextYear, 1, 1, 0, 0);
        }
        return temporalBoundary;
    }
}
