package ru.j4jdraft.tools.sql;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlMakerTest {

    private static final String NL = System.lineSeparator();

    @Test
    public void testInsert() {
        String[] fields = {"id", "name", "age"};
        String data = "S1001 Smith 30";
        String expected = "INSERT INTO table1 (id, name, age) VALUES ('S1001', 'Smith', 30);";
        String result = new SqlMaker().insert("table1", fields, data);
        assertThat(result, is(expected));
    }

    @Test
    public void testInsertLines() {
        String[] fields = {"id", "name", "age"};
        String data = "S1001 Smith 30" + NL
                + "S1002 Doe 40" + NL;
        String expected = "INSERT INTO table1 (id, name, age) VALUES ('S1001', 'Smith', 30);" + NL
                        + "INSERT INTO table1 (id, name, age) VALUES ('S1002', 'Doe', 40);" + NL;
        String result = new SqlMaker().insertLines("table1", fields, data);
        assertThat(result, is(expected));
    }
}
