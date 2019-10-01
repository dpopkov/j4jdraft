package ru.j4jdraft.vacparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

public class DocumentLoader implements Function<String, Optional<Document>> {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentLoader.class);

    @Override
    public Optional<Document> apply(String url) {
        try {
            // todo: decide whether to use any sleeping before the connect (robot rule)
            // todo: may be use timestamp of the last load and compare with minimal time span between loads
            Document document = Jsoup.connect(url).get();
            return Optional.of(document);
        } catch (IOException e) {
            LOG.error("Error loading url: " + url, e);
            return Optional.empty();
        }
    }
}
