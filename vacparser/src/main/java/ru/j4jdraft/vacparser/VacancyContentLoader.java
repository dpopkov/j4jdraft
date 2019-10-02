package ru.j4jdraft.vacparser;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.j4jdraft.vacparser.model.Vacancy;
import ru.j4jdraft.vacparser.parsers.VacancyPageParser;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Loads vacancy content.
 */
public class VacancyContentLoader implements Consumer<Vacancy> {
    private static final Logger LOG = LoggerFactory.getLogger(VacancyContentLoader.class);

    private final Function<String, Optional<Document>> documentLoader;
    private final VacancyPageParser vacancyParser;

    public VacancyContentLoader(Function<String, Optional<Document>> documentLoader,
                                VacancyPageParser vacancyParser) {
        this.documentLoader = documentLoader;
        this.vacancyParser = vacancyParser;
    }

    @Override
    public void accept(Vacancy vacancy) {
        String link = vacancy.getLink();
        LOG.trace("Loading content for vacancy at {}", link);
        Optional<Document> loadResult = documentLoader.apply(link);
        if (loadResult.isPresent()) {
            vacancyParser.parseAndFill(loadResult.get(), vacancy);
        } else {
            LOG.warn("Could not load information for vacancy: {}\n{}", vacancy.getName(), vacancy.getLink());
        }
    }
}
