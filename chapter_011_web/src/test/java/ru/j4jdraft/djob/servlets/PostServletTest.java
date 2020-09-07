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
import ru.j4jdraft.djob.model.Post;
import ru.j4jdraft.djob.store.PsqlStore;
import ru.j4jdraft.djob.store.Store;
import ru.j4jdraft.djob.store.StoreStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {
    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    private final Store stub = new StoreStub();
    private final PostServlet servlet = new PostServlet();

    @Before
    public void setup() {
        PowerMockito.mockStatic(PsqlStore.class);
    }

    @Test
    public void whenGetActionIsListThenAddsAllPostsToResponse() throws Exception {
        when(req.getParameter("action")).thenReturn("list");
        when(req.getRequestDispatcher(anyString())).thenReturn(mock(RequestDispatcher.class));
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Collection<Post>> captor = ArgumentCaptor.forClass(Collection.class);
        doNothing().when(req).setAttribute(anyString(), captor.capture());
        stub.save(new Post(1, "Alice"));
        stub.save(new Post(2, "Bob"));
        when(PsqlStore.getInstance()).thenReturn(stub);

        servlet.doGet(req, resp);

        Collection<Post> posts = captor.getValue();
        assertThat(posts, hasItem(havingName("Alice")));
        assertThat(posts, hasItem(havingName("Bob")));
    }

    @Test
    public void whenGetActionIsAddThenForwardsToEdit() throws Exception {
        when(req.getParameter("action")).thenReturn("add");
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req).getRequestDispatcher("post/edit.jsp");
        verify(dispatcher).forward(req, resp);
    }

    @Test
    public void whenPostThenSavesThePost() throws Exception {
        when(req.getParameter("id")).thenReturn("3");
        when(req.getParameter("name")).thenReturn("Bobby");
        when(req.getRequestDispatcher(anyString())).thenReturn(mock(RequestDispatcher.class));
        when(PsqlStore.getInstance()).thenReturn(stub);

        servlet.doPost(req, resp);

        assertThat(stub.findPostById(3).getName(), is("Bobby"));
    }

    private static Matcher<Post> havingName(String name) {
        return new PostMatcher(name);
    }

    public static class PostMatcher extends CustomMatcher<Post> {
        private final String name;

        public PostMatcher(String name) {
            super("Post with a name that equals " + name);
            this.name = name;
        }

        @Override
        public boolean matches(Object item) {
            return (item instanceof Post) && ((Post) item).getName().equals(name);
        }
    }
}