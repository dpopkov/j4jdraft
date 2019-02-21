package ru.j4jdraft.netw;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Demonstrates HttpURLConnection
 */
public class HttpUrlDemo {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.google.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        System.out.println("Request method: " + conn.getRequestMethod());
        System.out.println("Response code: " + conn.getResponseCode());
        System.out.println("Response message: " + conn.getResponseMessage());
        Map<String, List<String>> headers = conn.getHeaderFields();
        System.out.println("\nHeaders:");
        headers.forEach((k, v) -> System.out.printf("%s : %s%n", k, (v.size() == 1) ? v.get(0) : v));
    }
}
