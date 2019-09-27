package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ForumPageParser {
    private Document document;

    public ForumPageParser(Document document) {
        this.document = document;
    }

    public List<Vacancy> parse(int fromRow) {
        List<Vacancy> vacancies = new ArrayList<>();
        Element table = document.selectFirst("#content-wrapper-forum > table.forumTable > tbody");
        Elements rows = table.select("tr");
        for (int i = fromRow; i < rows.size(); i++) {
            Element row = rows.get(i);
            Element a = row.selectFirst("td.postslisttopic > a:first-child");
            String text = a.text();
            String href = a.attr("href");
            Vacancy vacancy = new Vacancy(text, href);
            vacancies.add(vacancy);
        }
        return vacancies;
    }

    public String nextPageUrl() {
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
