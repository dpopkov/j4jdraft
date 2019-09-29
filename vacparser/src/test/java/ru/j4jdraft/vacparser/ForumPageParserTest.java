package ru.j4jdraft.vacparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ForumPageParserTest {
    public static final String TEST_PAGE = "html/forum_page_test.html";
    public static final String CHARSET_NAME = "windows-1251";

    @Test
    public void whenParseThenCanFindAllVacancies() throws IOException, URISyntaxException {
        Document doc = Jsoup.parse(ResourceReader.read(TEST_PAGE, Charset.forName(CHARSET_NAME)));
        ForumPageParser parser = new ForumPageParser(doc);
        List<Vacancy> vacancies = parser.parse(0);
        assertThat(vacancies.size(), is(2));
        Vacancy vacancy1 = vacancies.get(0);
        assertThat(vacancy1.getName(), is("SQL разработчик в отчетность (Москва)"));
        assertThat(vacancy1.getLink(), is("https://www.sql.ru/forum/1317171/sql-razrabotchik-v-otchetnost-moskva"));
        Vacancy vacancy2 = vacancies.get(1);
        assertThat(vacancy2.getName(), is("DBA mariaDB Москва"));
        assertThat(vacancy2.getLink(), is("https://www.sql.ru/forum/1317271/dba-mariadb-moskva"));
    }

    @Test
    public void whenNextPageUrlThenReturnsLinkToPageAfterCurrent() throws IOException, URISyntaxException {
        Document doc = Jsoup.parse(ResourceReader.read(TEST_PAGE, Charset.forName(CHARSET_NAME)));
        ForumPageParser parser = new ForumPageParser(doc);
        String nextPageLink = parser.nextPageUrl();
        String expected = "https://www.sql.ru/forum/job-offers/2";
        assertThat(nextPageLink, is(expected));
    }
}
