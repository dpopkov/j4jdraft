package ru.j4jdraft.vacparser.research;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class HowToGetPage {
    public static void main(String[] args) throws IOException, InterruptedException {
        Properties props = new Properties();
        props.load(HowToGetPage.class.getResourceAsStream("/vacparser_app.properties"));
        String query = props.getProperty("site.url");
        if (args.length == 1) {
            query = args[0];
        }
        HttpResponse<String> response = request(query);
        String path = "tmp/tmp2.html";
        save(response.body(), path);
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
