package ru.j4jdraft.djob.servlets;

import ru.j4jdraft.djob.model.Candidate;
import ru.j4jdraft.djob.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        if ("list".equals(action)) {
            listCandidates(req, resp);
        } else if ("add".equals(action)) {
            req.getRequestDispatcher("candidate/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveCandidate(req);
        listCandidates(req, resp);
    }

    private void listCandidates(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Candidate> candidates = PsqlStore.getInstance().findAllCandidates();
        req.setAttribute("candidates", candidates);
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    private void saveCandidate(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Candidate candidate = new Candidate(id, name);
        PsqlStore.getInstance().save(candidate);
    }
}
