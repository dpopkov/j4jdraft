package ru.j4jdraft.vacparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class VacancyPageParserTest {
    private static final String TEST_PAGE = "html/vacancy_page_test.html";
    private static final String CHARSET_NAME = "windows-1251";

    @Test
    public void whenFillFromDocumentThenVacancyReceivesContentForFields() throws IOException, URISyntaxException {
        String html = ResourceReader.read(TEST_PAGE, Charset.forName(CHARSET_NAME));
        Document doc = Jsoup.parse(html);
        VacancyPageParser parser = new VacancyPageParser();
        Vacancy vacancy = new Vacancy("name", "link");
        String expectedDescription = "<b>Вакансия: Администратор Oracle</b><br>"
                + "Вакансия в городе: Москва, м. \"Чистые пруды\"";
        LocalDateTime expectedCreated = LocalDateTime.of(2019, 9, 25, 15, 0);
        parser.parseAndFill(doc, vacancy);
        assertThat(vacancy.getDescription(), is(expectedDescription));
        assertThat(vacancy.getCreated(), is(expectedCreated));
    }
}
