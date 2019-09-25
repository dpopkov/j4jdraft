package ru.j4jdraft.vacparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

public class VacanciesScraper implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(VacanciesScraper.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        AppSettings settings = (AppSettings) context.getJobDetail().getJobDataMap().get("appSettings");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(settings.jdbcUrl(), settings.jdbcUser(), settings.jdbcPassword());
            Storage storage = new DbStorage(connection);
            String pageUrl = settings.siteUrl();

            LocalDateTime temporalBoundary;
            Vacancy lastVacancy = storage.findLast();
            if (lastVacancy != null) {
                temporalBoundary = lastVacancy.getCreated();
            } else {
                int nextYear = LocalDate.now().plusYears(1L).getYear();
                temporalBoundary = LocalDateTime.of(nextYear, 1, 1, 0, 0);
            }

            Predicate<Vacancy> predicateByTime = vacancy -> vacancy.getCreated().isAfter(temporalBoundary);
            PageProcessor processor = new PageProcessor(storage, predicateByTime);

            Document page = Jsoup.connect(pageUrl).get();
            Optional<String> result = processor.process(page);
            while (result.isPresent()) {
                pageUrl = result.get();
                page = Jsoup.connect(pageUrl).get();
                result = processor.process(page);
            }
        } catch (SQLException e) {
            LOG.error("Database error", e);
        } catch (IOException e) {
            LOG.error("Error downloading page", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("Error Closing connection", e);
                }
            }
        }
    }
}
