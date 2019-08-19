package ru.j4jdraft.sqlite;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConvertXSLTTest {

    @Test
    public void whenConvertThenSourceFileIsConvertedUsingXslTransformation() throws Exception {
        Path testDir = Files.createTempDirectory(ConvertXSLTTest.class.getName());
        Path dest = testDir.resolve("ConvertXslt.output.xml");
        Path source = pathToResource("xml/ConvertXslt.input.xml");
        Path xslt = pathToResource("xml/ConvertXslt.transform.xml");
        String expected = String.join(System.lineSeparator(),
                "<entries>",
                "    <entry field=\"10\"/>",
                "    <entry field=\"20\"/>",
                "</entries>",
                "");
        ConvertXSLT converter = new ConvertXSLT();
        try {
            converter.convert(source, dest, xslt);
            assertThat(Files.exists(dest), is(true));
            String transformedXml = Files.readString(dest);
            assertThat(transformedXml, is(expected));
        } finally {
            Files.deleteIfExists(dest);
            Files.deleteIfExists(testDir);
        }
    }

    private static Path pathToResource(String name) throws URISyntaxException {
        URL url = ConvertXSLTTest.class.getClassLoader().getResource(name);
        if (url == null) {
            throw new IllegalArgumentException("Can not locate resource: " + name);
        }
        return Paths.get(url.toURI());
    }
}