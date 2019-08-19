package ru.j4jdraft.sqlite.xsltusage;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

public class XsltExample {
    public static void main(String[] args) throws IOException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        try (InputStream xslInput = getStream("xml/xsl.xml");
             InputStream xmlInput = getStream("xml/user.xml")) {
            Transformer transformer = factory.newTransformer(new StreamSource(xslInput));
            transformer.transform(new StreamSource(xmlInput), new StreamResult(System.out)
            );
        }
    }

    private static InputStream getStream(String name) {
        return XsltExample.class.getClassLoader().getResourceAsStream(name);
    }
}
