package ru.j4jdraft.howto.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class ParsePage {
    public static void main(String[] args) throws IOException {
        parsePageLink();
//        parseRows();
    }

    private static void parsePageLink() throws IOException {
        String path = "tmp/page1.html";
        File file = new File(path);
        Document doc = Jsoup.parse(file, "windows-1251");
        Element tdPages = doc.selectFirst("#content-wrapper-forum > table:nth-child(6) > tbody > tr > td:nth-child(1)");
        Element b = tdPages.selectFirst("b");
        String currentStr = b.text();
        int current = Integer.parseInt(currentStr);
        System.out.println("current = " + current);
        Element nextElement = b.nextElementSibling();
        if (nextElement != null) {
            Element a = nextElement.selectFirst("a");
            if (a != null) {
                String href = a.attr("href");
                System.out.println("href = " + href);
            }
        }
    }

    private static void parseRows() throws IOException {
        String path = "tmp/page1.html";
        File file = new File(path);
        Document doc = Jsoup.parse(file, "windows-1251");
        Element table = doc.selectFirst("#content-wrapper-forum > table.forumTable > tbody");
        Elements rows = table.select("tr");
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Element a = row.selectFirst("td.postslisttopic > a:first-child");
            String text = a.text();
            String href = a.attr("href");
            System.out.printf("%s  ==  %s%n", text, href);
        }
    }
}
