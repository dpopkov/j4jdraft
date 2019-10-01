package ru.j4jdraft.vacparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import ru.j4jdraft.vacparser.model.Vacancy;
import ru.j4jdraft.vacparser.parsers.ForumPageParser;
import ru.j4jdraft.vacparser.storage.Storage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ForumPageProcessorTest {
    public static final String TEST_PAGE = "html/forum_page_test.html";

    @Test
    public void whenProcessDocumentThenGetsAllVacancies() {
        // todo: implement
    }

    @Test
    public void whenProcessDocumentThenReturnsNextPageUrl() throws IOException, SQLException {
        // todo: НУЖНА СХЕМА ТЕСТИРОВАНИЯ КОМПОНЕНТОВ
        /*
        Результат работы:
        1) из документа получены вакансии,

        2.1) в хранилище идут только отфильтрованные и заполненные вакансии
        2.2) в хранилище не попадают вакансии дубликаты
        2.3) отфильтрованные вакансии получают данные в доп.поля

        3.1) если док-т не выходит за врем.границу, то возвращается след.адрес
        3.2) если документ выходит за временную границу, то не возвращается

        Нужно замокать:
        Storage - verify что туда отправлены только ожидаемые вакансии,
        ForumPageParser - должен возращать рез-т пригодный для этого теста



         */

        Storage storage = mock(Storage.class);
        ForumPageParser pageParser = mock(ForumPageParser.class);
        // todo: make page loader return documents for vacancies
        Function<String, Optional<Document>> pageLoader = url -> Optional.empty();
        Consumer<Vacancy> vacancyLoader = v -> {};

        ForumPageProcessor processor = new ForumPageProcessor(storage, pageParser, s -> true, v -> true,
                vacancyLoader);
        String html = ResourceReader.readWin1251(TEST_PAGE);
        Document forumPage = Jsoup.parse(html);
        Optional<String> result = processor.process(forumPage);
        assertThat(result.orElse("dummy"), is("https://www.sql.ru/forum/job-offers/2"));
    }

    @Test
    public void whenProcessDocumentThenAddsFilteredVacanciesToStorage() {
        // todo: implement
    }

    // todo: test skip and pass predicates
}
