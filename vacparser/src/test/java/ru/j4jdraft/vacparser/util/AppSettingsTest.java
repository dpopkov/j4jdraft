package ru.j4jdraft.vacparser.util;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AppSettingsTest {
    @Test
    public void whenLoadedThenReturnCorrectSettings() {
        AppSettings settings = new AppSettings("test_vacparser_settings_app.properties");
        assertThat(settings.siteUrl(), is("site-url"));
        assertThat(settings.jdbcUrl(), is("db-url"));
        assertThat(settings.jdbcUser(), is("user"));
        assertThat(settings.jdbcPassword(), is("123"));
        assertThat(settings.cronTime(), is("0 1 2 * * ?"));
        assertThat(settings.crawlDelay(), is(1234));
    }
}
