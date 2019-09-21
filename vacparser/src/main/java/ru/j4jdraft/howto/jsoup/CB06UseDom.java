package ru.j4jdraft.howto.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Cookbook 6. Use DOM methods to navigate a document.
 */
public class CB06UseDom {
    public static void main(String[] args) throws IOException {
        String path = System.getenv("USERPROFILE") + File.separator + "Downloads\\Telegram Desktop\\job4j.ru__ChatExport_24_05_2019\\messages33.html";
        File file = new File(path);
        Document doc = Jsoup.parse(file, "UTF-8");
        Elements messages = doc.getElementsByClass("message default clearfix");
        for (Element e : messages) {
            Element id = e.selectFirst(".initials");
            System.out.println("id.text() = " + id.text());
            Element text = e.selectFirst(".text");
            System.out.println("text.text() = " + text.text());
        }
    }
}
