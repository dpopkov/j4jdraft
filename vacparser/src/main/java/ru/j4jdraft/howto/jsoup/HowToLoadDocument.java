package ru.j4jdraft.howto.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class HowToLoadDocument {
    private static final Logger LOGGER = LoggerFactory.getLogger(HowToLoadDocument.class);

    public static void main(String[] args) throws IOException {
        final String url = "http://en.wikipedia.org";
        Document doc = Jsoup.connect(url).get();
        LOGGER.info(doc.title());
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headLine : newsHeadlines) {
            LOGGER.info("{}\n\t{}", headLine.attr("title"), headLine.absUrl("href"));
        }
    }
}
