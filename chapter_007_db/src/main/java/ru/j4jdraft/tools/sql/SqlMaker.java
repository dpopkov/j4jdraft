package ru.j4jdraft.tools.sql;

import java.util.regex.Pattern;

/**
 * Creates SQL statements using raw data.
 */
public class SqlMaker {
    private static final char SINGLE_QUOTE = '\'';
    private static final Pattern INTEGER_PATTERN = Pattern.compile("\\d+");
    private static final String LINE_DIVIDER = "\r\n|\n";

    public String insert(String table, String[] fields, String data) {
        StringBuilder sb = new StringBuilder();
        insertToBuilder(sb, table, fields, data);
        return sb.toString();
    }

    public String insertLines(String table, String[] fields, String[] dataLines) {
        StringBuilder sb = new StringBuilder();
        for (String line : dataLines) {
            insertToBuilder(sb, table, fields, line);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String insertLines(String table, String[] fields, String dataLines) {
        return insertLines(table, fields, dataLines.split(LINE_DIVIDER));
    }

    private void insertToBuilder(StringBuilder sb, String table, String[] fields, String data) {
        sb.append("INSERT INTO ");
        sb.append(table);
        sb.append(" (");
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(fields[i]);
        }
        sb.append(") VALUES (");
        String[] dataTokens = data.split(" ");
        for (int i = 0; i < dataTokens.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            String value = dataTokens[i];
            boolean useQuotes = !isInteger(value);
            if (useQuotes) {
                sb.append(SINGLE_QUOTE);
            }
            sb.append(value);
            if (useQuotes) {
                sb.append(SINGLE_QUOTE);
            }
        }
        sb.append(");");
    }

    private boolean isInteger(String value) {
        return INTEGER_PATTERN.matcher(value).matches();
    }
}
