package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VacanciesScraper implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(VacanciesScraper.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        DbConnector connector = (DbConnector) dataMap.get("dbConnector");
        Connection connection = null;
        try {
            connection = connector.connect();
            Storage storage = new DbStorage(connection);
            // Можно сохранить время запуска в БД
            // если оно там есть, значит запуск не первый и нужно скачивать только новые

            // Вопрос: какие вакансии считаются новыми, каков критерий

        /*
        Если запуск первый, то собираются все объявления с начала года,
        если не первый, то собираются все новые объявления с последнего запуска.
         */

            final String siteUrl = dataMap.getString("siteUrl");
            // Получить 1-ю страницу
            PageLoader loader = new PageLoader();
            Document page = loader.loadFrom(siteUrl);

            // Все действия по обработке страницы и сохранению вакансий из нее в БД
            // могут быть инкапсулированы в класс PageProcessor(storage, критерийОстановки)
            // method: boolean process(page); OR Optional<nextPageUrl> process(page)

            // получить из страницы вакансии
            PageParser parser = new PageParser(page);
            List<Vacancy> vacancies = parser.vacancies();

            // сохранить вакансии в БД
            storage.add(vacancies);

            // Проверить нужно ли обрабатывать следующую страницу

            // из страницы получить адрес следующей
            String pageUrl = parser.nextPageUrl();

        } catch (SQLException e) {
            LOG.error("Database error", e);
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
