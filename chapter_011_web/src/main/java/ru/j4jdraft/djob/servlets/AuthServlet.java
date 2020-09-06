package ru.j4jdraft.djob.servlets;

import ru.j4jdraft.djob.model.User;
import ru.j4jdraft.djob.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            req.getSession().invalidate();
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = PsqlStore.getInstance().findUserByEmail(email);
        if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("authError", "Email or password is incorrect");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
