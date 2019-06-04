package ru.j4jdraft.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class JdbcHelper implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcHelper.class);
    private static final String PROPERTIES_FILENAME = "db.properties";
    private static final String BASE_URL = "jdbc:postgresql://localhost:5432/";
    private static final String POSTGRES_URL = BASE_URL + "postgres";
    private Connection connection;

    public JdbcHelper(Properties properties) throws SQLException {
        connection = DriverManager.getConnection("url", properties);
    }

    /** Returns true if the db exists already, false if the db was created. */
    public static boolean ensureDbExists(Properties dbProperties, String dbName, String scriptName) throws SQLException, IOException {
        final String checkDbSql = "SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower(?)";
        boolean exists;
        try (Connection pgConnection = DriverManager.getConnection(POSTGRES_URL, dbProperties)) {
            PreparedStatement ps = pgConnection.prepareStatement(checkDbSql);
            ps.setString(1, dbName);
            ResultSet result = ps.executeQuery();
            exists = result.next();
            if (!exists) {
                final String createDbSql = "create database " + dbName;
                Statement st = pgConnection.createStatement();
                st.execute(createDbSql);
            }
        }
        if (exists) {
            return true;
        } else {
            List<String> sqlList = ResourceReader.readSqlStatements(scriptName);
            try (Connection connection = DriverManager.getConnection(BASE_URL + dbName, dbProperties)) {
                Statement st = connection.createStatement();
                for (String sql : sqlList) {
                    st.addBatch(sql);
                }
                st.executeBatch();
            }
            return false;
        }
    }

    public static boolean createDb(Properties dbProperties, String dbName) throws SQLException {
        final String createDbSql = "create database " + dbName;
        boolean created;
        try (Connection pgConnection = DriverManager.getConnection(POSTGRES_URL, dbProperties)) {
            Statement st = pgConnection.createStatement();
            created = st.execute(createDbSql);
        }
        return created;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static Properties readProperties(String filename) {
        Properties props = new Properties();
        try {
            InputStream in = JdbcHelper.class.getClassLoader().getResourceAsStream(filename);
            if (in != null) {
                props.load(in);
            } else {
                LOG.error("Cannot load DB properties file: " + filename);
                System.exit(-1);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            System.exit(-1);
        }
        return props;
    }

    public static void main(String[] args) {
        String dbName = "test1";
        if (args.length == 1) {
            dbName = args[0];
        }
        Properties props = readProperties(PROPERTIES_FILENAME);
        try {
            boolean existed = ensureDbExists(props, dbName, "sql/createTestDb1.sql" );
            if (existed) {
                System.out.println("Db exists already");
            } else {
                System.out.println("Creation finished");
            }
        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
