package ru.j4jdraft.tracker;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@Ignore("Do not run unless it is necessary to test integration with PostgreSQL")
public class JdbcHelperIntegrationTest {

    public static final String DB_NAME = "db_test_42";

    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    @Test
    public void whenNotExistingDbThenCreatesDbAndOpensConnection() throws IOException, SQLException {
        Properties props = JdbcHelper.readProperties(JdbcHelper.PROPERTIES_FILENAME);
        JdbcHelper helper = new JdbcHelper(props);
        try (Connection postgresConnection = DriverManager.getConnection(props.getProperty("baseUrl") + "postgres", props)) {
            assertThat(helper.dbExists(postgresConnection, DB_NAME), is(false));
            Connection dbConnection = null;
            try {
                dbConnection = helper.connectToExistingDb(DB_NAME, "sql/createTestDb1.sql");
                assertNotNull("This connection should not be null", dbConnection);
            } finally {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            }
            assertThat(helper.dbExists(postgresConnection, DB_NAME), is(true));
            helper.dropDb(postgresConnection, DB_NAME);
            assertThat(helper.dbExists(postgresConnection, DB_NAME), is(false));
        }
    }
}
