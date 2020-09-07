package ru.j4jdraft.djob.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.j4jdraft.djob.store.PsqlStore;
import ru.j4jdraft.djob.store.Store;
import ru.j4jdraft.djob.store.StoreStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class RegServletTest {
    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    private final Store stub = new StoreStub();
    private final RegServlet servlet = new RegServlet();

    @Before
    public void setup() {
        PowerMockito.mockStatic(PsqlStore.class);
    }

    @Test
    public void whenPostThenSavesUserToStore() throws IOException {
        when(req.getParameter("name")).thenReturn("name1");
        when(req.getParameter("email")).thenReturn("email1");
        when(req.getParameter("password")).thenReturn("password1");
        when(PsqlStore.getInstance()).thenReturn(stub);

        servlet.doPost(req, resp);

        assertThat(stub.findUserByEmail("email1").getPassword(), is("password1"));
    }
}
