package ru.j4jdraft.djob.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.j4jdraft.djob.store.PsqlStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadServlet extends HttpServlet {
    private static final String IMAGES_DIR_NAME = "images";
    private static final File IMAGES_DIRECTORY = new File(IMAGES_DIR_NAME);

    @Override
    public void init() throws ServletException {
        ensureDirectory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listImages(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext context = getServletContext();
        File imageRepository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(imageRepository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    int id = PsqlStore.getInstance().nextPhotoId();
                    File file = new File(IMAGES_DIRECTORY, Integer.toString(id));
                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        listImages(req, resp);
    }

    private void listImages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> images = new ArrayList<>();
        File[] files = IMAGES_DIRECTORY.listFiles();
        if (files != null) {
            for (File file : files) {
                images.add(file.getName());
            }
        }
        req.setAttribute("images", images);
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }

    private void ensureDirectory() throws ServletException {
        if (!UploadServlet.IMAGES_DIRECTORY.exists()) {
            boolean created = UploadServlet.IMAGES_DIRECTORY.mkdirs();
            if (!created) {
                throw new ServletException("Failed to create directory: " + IMAGES_DIRECTORY);
            }
        }
    }
}
