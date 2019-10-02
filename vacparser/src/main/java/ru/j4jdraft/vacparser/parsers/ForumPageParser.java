package ru.j4jdraft.vacparser.parsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.j4jdraft.vacparser.model.ForumPage;
import ru.j4jdraft.vacparser.model.Vacancy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ForumPageParser {
    /**
     * Парсит страницу форума и возвращает результат в виде списка вакансий и ссылки на следующую страницу.
     * @param document document representing forum page
     * @param skipRows number of rows on the page that should be skipped
     * @return forum page object
     */
    public ForumPage parse(Document document, int skipRows) {
        ForumPage forumPage = new ForumPage();
        List<Vacancy> vacancies = parseVacancies(document, skipRows);
        String nextPage = parseNextPageUrl(document);
        forumPage.setVacancies(vacancies);
        forumPage.setNextPage(nextPage);
        return forumPage;
    }

    private List<Vacancy> parseVacancies(Document document, int skipRows) {
        LinkedHashMap<String, Vacancy> map = new LinkedHashMap<>();
        Element table = document.selectFirst("#content-wrapper-forum > table.forumTable > tbody");
        Elements rows = table.select("tr");
        for (int i = skipRows; i < rows.size(); i++) {
            Element row = rows.get(i);
            Element a = row.selectFirst("td.postslisttopic > a:first-child");
            String name = a.text();
            if (!map.containsKey(name)) {
                String href = a.attr("href");
                Vacancy vacancy = new Vacancy(name, href);
                map.put(name, vacancy);
            }
        }
        return new ArrayList<>(map.values());
    }

    private String parseNextPageUrl(Document document) {
        Element pages = document.selectFirst("#content-wrapper-forum > table:nth-child(6) > tbody > tr > td:nth-child(1)");
        Element currentPage = pages.selectFirst("b");
        Element nextLink = currentPage.nextElementSibling();
        if (nextLink != null) {
            Element a = nextLink.selectFirst("a");
            if (a != null) {
                return a.attr("href");
            }
        }
        return null;
    }
}
