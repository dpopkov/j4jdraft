package ru.j4jdraft.tools.sql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
Used to create insert sql statements using just raw data as input.
 */
public class SqlMakerMain {
    public static void main(String[] args) throws IOException {
        String inFile = "txt/in-data.txt";
        String outFile = "txt/out-data.txt";
        if (args.length == 2) {
            inFile = args[0];
            outFile = args[1];
        }
        if (inFile == null || outFile == null) {
            System.out.println("Cancelled");
            System.exit(0);
        }
        List<String> lines = Files.readAllLines(Paths.get(inFile));
        String sql = runSqlMaker(lines);
        Files.writeString(Paths.get(outFile), sql);
        System.exit(0);
    }

    private static String runSqlMaker(List<String> lines) {
        String table = lines.get(0);
        String fieldsLine = lines.get(1);
        SqlMaker maker = new SqlMaker();
        String[] dataLines = lines.subList(2, lines.size()).toArray(new String[0]);
        String[] fields = fieldsLine.split(" ");
        return maker.insertLines(table, fields, dataLines);
    }
}
