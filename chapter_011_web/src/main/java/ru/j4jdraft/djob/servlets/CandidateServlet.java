package ru.j4jdraft.djob.servlets;

import ru.j4jdraft.djob.Store;
import ru.j4jdraft.djob.model.Candidate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Candidate candidate = new Candidate(id, name);
        Store.getInstance().save(candidate);
        resp.sendRedirect(req.getContextPath() + "/candidates.jsp");
    }
}
