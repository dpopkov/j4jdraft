package ru.j4jdraft.sqlite.xmlusage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

/** Example of how to save XML file. */
public class XmlUsage {
    @XmlRootElement
    public static class User {
        private List<Field> values;

        public User() {
        }

        public User(List<Field> values) {
            this.values = values;
        }

        public List<Field> getValues() {
            return values;
        }

        public void setValues(List<Field> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Field {
        private int value;

        public Field() {
        }

        public Field(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new User(Arrays.asList(new Field(1), new Field(2), new Field(3))),
                System.out);
    }
}
