package ru.j4jdraft.djob.servlets;

import ru.j4jdraft.djob.Store;
import ru.j4jdraft.djob.model.Post;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        Post post = new Post(0, name, LocalDate.now());
        Store.getInstance().save(post);
        resp.sendRedirect(req.getContextPath() + "/posts.jsp");
    }
}
