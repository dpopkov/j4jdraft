package ru.j4jdraft.netw;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Demonstrate URLConnection.
 */
public class UrlConnectionDemo {
    public static void main(String[] args) throws IOException {
        String urlString = "http://www.internic.net";
        if (args.length > 0) {
            urlString = args[0];
        }
        URL hp = new URL(urlString);
        URLConnection hpCon = hp.openConnection();
        displayDate("Date", hpCon.getDate());
        System.out.println("Content-Type: " + hpCon.getContentType());
        displayDate("Expires", hpCon.getExpiration());
        displayDate("Last Modified", hpCon.getLastModified());
        long len = hpCon.getContentLength();
        if (len == -1) {
            System.out.println("Content length unavailable");
        } else {
            System.out.println("Content-length: " + len);
        }
        if (len > 0) {
            System.out.println("===Content===");
            InputStream in = hpCon.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
            in.close();
        } else {
            System.out.println("No content available");
        }
    }

    private static void displayDate(String label, long milliseconds) {
        if (milliseconds == 0) {
            System.out.printf("No '%s' information%n", label);
        } else {
            System.out.printf("%s: %s%n", label, new Date(milliseconds).toString());
        }
    }
}
