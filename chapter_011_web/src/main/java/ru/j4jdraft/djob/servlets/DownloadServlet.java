package ru.j4jdraft.djob.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    private static final String IMAGES_DIR_NAME = "images";
    private static final File IMAGES_DIRECTORY = new File(IMAGES_DIR_NAME);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String photoName = req.getParameter("name");
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + photoName + "\"");
        File photoFile = new File(IMAGES_DIRECTORY, photoName);
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(photoFile))) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }
}
