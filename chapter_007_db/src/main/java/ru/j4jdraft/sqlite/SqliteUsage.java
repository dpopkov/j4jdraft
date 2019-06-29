package ru.j4jdraft.sqlite;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;

@SuppressWarnings("SqlResolve")
public class SqliteUsage {
    public static void main(String[] args) {
        Path path = Paths.get("db");
        String dirPath = path.toAbsolutePath().toString() + File.separator;
        String dbName = "test.db";
        if (args.length == 1) {
            dbName = args[0];
        }
        final String url = "jdbc:sqlite:" + dirPath + dbName;
        System.out.println("Using url: " + url);
        Scanner in = new Scanner(System.in);
        System.out.print("c - create, w - write, r - read: ");
        String cmd = in.nextLine();
        try {
            if ("c".equals(cmd)) {
                createNewDatabase(url);
            } else if ("w".equals(cmd)) {
                System.out.print("Enter number of records: ");
                int numRecords = in.nextInt();
                writeData(url, numRecords);
            } else if ("r".equals(cmd)) {
                readData(url);
            } else {
                System.out.println("Invalid command: " + cmd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readData(String url) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            String selectSql = "select id, name from table1 order by id";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectSql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%d : %s%n", id, name);
            }
            rs.close();
        }
    }

    private static void writeData(String url, int numRecords) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            String createSql = "create table if not exists table1 (id int, name varchar(30))";
            Statement stmt = conn.createStatement();
            stmt.execute(createSql);
            stmt.close();
            String insertSql = "insert into table1 (id, name) values (?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertSql);
            for (int i = 1; i <= numRecords; i++) {
                ps.setInt(1, i);
                ps.setString(2, "name" + i);
                ps.executeUpdate();
            }
            ps.close();
        }
    }

    private static void createNewDatabase(String url) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created");
            }
        }
    }
}
