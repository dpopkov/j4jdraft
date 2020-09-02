package ru.j4jdraft.djob.servlets;

import ru.j4jdraft.djob.model.Post;
import ru.j4jdraft.djob.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        if ("list".equals(action)) {
            listPosts(req, resp);
        } else if ("add".equals(action)) {
            req.getRequestDispatcher("post/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        savePost(req);
        listPosts(req, resp);
    }

    private void listPosts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Post> posts = PsqlStore.getInstance().findAllPosts();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("posts.jsp").forward(req, resp);
    }

    private void savePost(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Post post = new Post(id, name);
        PsqlStore.getInstance().save(post);
    }
}
