package ru.j4jdraft.sqlite;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainRead {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Path path = Paths.get("new.xml");
        SAXCalculator calc = new SAXCalculator("entry", "field");
        long start = System.currentTimeMillis();
        long sum = calc.sum(path);
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed " + (finish - start) + " ms");
        System.out.println("sum = " + sum);
    }
}
