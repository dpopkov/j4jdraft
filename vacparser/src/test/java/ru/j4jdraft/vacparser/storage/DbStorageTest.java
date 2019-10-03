package ru.j4jdraft.vacparser.storage;

import org.junit.Ignore;
import org.junit.Test;
import ru.j4jdraft.vacparser.model.Vacancy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ru.j4jdraft.vacparser.storage.DbStorage.ADD_VACANCY;

@Ignore
public class DbStorageTest {
    private static final LocalDateTime NOW = LocalDateTime.now();

    private final Connection connection = mock(Connection.class);
    private final PreparedStatement statement = mock(PreparedStatement.class);

    @Test
    public void testAdd() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(connection.prepareStatement(ADD_VACANCY, RETURN_GENERATED_KEYS)).thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        Storage storage = new DbStorage(connection);
        Vacancy vacancy = new Vacancy("Bob", "Java trainee", "example.com", NOW, NOW);
        assertNull(vacancy.getId());
        Vacancy added = storage.add(vacancy);
        assertNotNull(added.getId());
        verify(connection).prepareStatement(ADD_VACANCY, RETURN_GENERATED_KEYS);
        verify(statement).setString(1, "Bob");
        verify(statement).setString(2, "Java trainee");
        verify(statement).setString(3, "example.com");
        verify(statement).setObject(4, NOW);
        verify(statement).setObject(5, NOW);
        verify(statement).executeUpdate();
    }
}