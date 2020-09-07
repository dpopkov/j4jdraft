package ru.j4jdraft.djob.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.j4jdraft.djob.model.User;
import ru.j4jdraft.djob.store.PsqlStore;
import ru.j4jdraft.djob.store.Store;
import ru.j4jdraft.djob.store.StoreStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class AuthServletTest {
    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    private final Store stub = new StoreStub();
    private final AuthServlet servlet = new AuthServlet();

    @Before
    public void setup() {
        PowerMockito.mockStatic(PsqlStore.class);
    }

    @Test
    public void whenGetLogoutThenInvalidate() throws Exception {
        when(req.getParameter("logout")).thenReturn("");
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("login.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doGet(req, resp);

        verify(session).invalidate();
    }

    @Test
    public void whenPostValidPasswordThenRedirectToPosts() throws Exception {
        when(req.getParameter("email")).thenReturn("root@local");
        when(req.getParameter("password")).thenReturn("root");
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("contextPath");
        stub.save(new User("Admin", "root@local", "root"));
        when(PsqlStore.getInstance()).thenReturn(stub);

        servlet.doPost(req, resp);

        verify(session).setAttribute(anyString(), any(User.class));
        verify(resp).sendRedirect("contextPath/posts.do");
    }

    @Test
    public void whenPostNotValidPasswordThenForwardsToLogin() throws Exception {
        when(req.getParameter("email")).thenReturn("root@local");
        when(req.getParameter("password")).thenReturn("123");
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(PsqlStore.getInstance()).thenReturn(stub);

        servlet.doPost(req, resp);

        verify(req).setAttribute("authError", "Email or password is incorrect");
        verify(req).getRequestDispatcher("login.jsp");
        verify(dispatcher).forward(req, resp);
    }
}
