package ru.j4jdraft.djob.servlets;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.store.PsqlStore;
import ru.j4jdraft.djob.store.Store;
import ru.j4jdraft.djob.store.StoreStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class CandidateServletTest {
    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    private final Store stub = new StoreStub();
    private final CandidateServlet servlet = new CandidateServlet();

    @Before
    public void setup() {
        PowerMockito.mockStatic(PsqlStore.class);
    }

    @Test
    public void whenGetActionIsListThenAddsAllCandidatesToResponse() throws Exception {
        when(req.getParameter("action")).thenReturn("list");
        when(req.getRequestDispatcher(anyString())).thenReturn(mock(RequestDispatcher.class));
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Collection<Candidate>> captor = ArgumentCaptor.forClass(Collection.class);
        doNothing().when(req).setAttribute(anyString(), captor.capture());
        stub.save(new Candidate(1, "Alice", "1"));
        stub.save(new Candidate(2, "Bob", "2"));
        when(PsqlStore.getInstance()).thenReturn(stub);

        servlet.doGet(req, resp);

        Collection<Candidate> candidates = captor.getValue();
        assertThat(candidates, hasItem(havingName("Alice")));
        assertThat(candidates, hasItem(havingName("Bob")));
    }

    @Test
    public void whenGetActionIsAddThenForwardsToEdit() throws Exception {
        when(req.getParameter("action")).thenReturn("add");
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req).getRequestDispatcher("candidate/edit.jsp");
        verify(dispatcher).forward(req, resp);
    }

    @Test
    public void whenPostThenSavesCandidate() throws Exception {
        when(req.getParameter("id")).thenReturn("3");
        when(req.getParameter("name")).thenReturn("Snow");
        when(req.getParameter("photoId")).thenReturn("4");
        when(req.getRequestDispatcher(anyString())).thenReturn(mock(RequestDispatcher.class));
        when(PsqlStore.getInstance()).thenReturn(stub);

        servlet.doPost(req, resp);

        assertThat(stub.findCandidateById(3).getName(), is("Snow"));
    }

    private static Matcher<Candidate> havingName(String name) {
        return new CandidateMatcher(name);
    }

    public static class CandidateMatcher extends CustomMatcher<Candidate> {
        private final String name;

        public CandidateMatcher(String name) {
            super("Candidate with a name that equals " + name);
            this.name = name;
        }

        @Override
        public boolean matches(Object item) {
            return (item instanceof Candidate) && ((Candidate) item).getName().equals(name);
        }
    }
}
