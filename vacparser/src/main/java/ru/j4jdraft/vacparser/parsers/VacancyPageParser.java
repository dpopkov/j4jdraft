package ru.j4jdraft.vacparser.parsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.j4jdraft.vacparser.model.Vacancy;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancyPageParser {
    private static final String DATE_TIME_REGEX = "\\d?\\d \\D{3} \\d{2}, \\d{2}:\\d{2}";
    private static final String MSG_DESCRIPTION_CSS_QUERY =
            "#content-wrapper-forum > table:nth-child(4) > tbody > tr:nth-child(2) > td:nth-child(2)";
    private static final String MSG_FOOTER_CSS_QUERY =
            "#content-wrapper-forum > table:nth-child(4) > tbody > tr:nth-child(3) > td";

    private final Pattern pattern = Pattern.compile(DATE_TIME_REGEX);
    private final DateTimeParser dateTimeParser = new DateTimeParser();

    /** Получает информацию из переданной страницы и сохраняет ее в вакансию. */
    public void parseAndFill(Document document, Vacancy target) {
        Element msgBody = document.selectFirst(MSG_DESCRIPTION_CSS_QUERY);
        String description = msgBody.html();
        target.setDescription(description);
        Element footer = document.selectFirst(MSG_FOOTER_CSS_QUERY);
        LocalDateTime created = parseDateTime(footer.html());
        target.setCreated(created);
    }

    private LocalDateTime parseDateTime(String msgFooter) {
        Matcher matcher = pattern.matcher(msgFooter);
        if (matcher.find()) {
            String found = matcher.group(0);
            return dateTimeParser.parse(found);
        } else {
            return null;
        }
    }
}
