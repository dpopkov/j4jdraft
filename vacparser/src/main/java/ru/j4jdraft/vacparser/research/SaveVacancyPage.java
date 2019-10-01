package ru.j4jdraft.vacparser.research;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.j4jdraft.vacparser.parsers.ForumPageParser;
import ru.j4jdraft.vacparser.model.Vacancy;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class SaveVacancyPage {
    public static void main(String[] args) throws IOException, InterruptedException {
        Properties props = new Properties();
        props.load(HowToGetPage.class.getResourceAsStream("/vacparser_app.properties"));
        String query = props.getProperty("site.url");
        if (args.length == 1) {
            query = args[0];
        }

        Document page = Jsoup.connect(query).get();
        ForumPageParser parser = new ForumPageParser();
        List<Vacancy> vacancies = parser.parse(page, 4).getVacancies();
        Vacancy vacancy = vacancies.get(0);
        String name = vacancy.getName();
        System.out.println("name = " + name);
        String href = vacancy.getLink();
        System.out.println("href = " + href);

        final String path = "tmp/vac_page.html";
        HttpResponse<String> response = request(href);
        save(response.body(), "tmp/vac_page.html");
        System.out.println("Saved to " + path);
    }

    private static HttpResponse<String> request(String query) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(query))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Query: " + query);
        System.out.println("Status code: " + response.statusCode());
        return response;
    }

    private static void save(String text, String path) throws IOException {
        Files.writeString(Paths.get(path), text, Charset.forName("windows-1251"));
    }
}
