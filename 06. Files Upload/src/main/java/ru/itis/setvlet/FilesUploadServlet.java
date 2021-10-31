package ru.itis.setvlet;

import org.springframework.context.ApplicationContext;
import ru.itis.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/filesUpload")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.filesService = springContext.getBean(FilesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/filesUpload.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        filesService.saveFileInfo(
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize()
        );

        response.sendRedirect("/filesUpload");
    }

}
