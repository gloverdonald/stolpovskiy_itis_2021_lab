package ru.itis.setvlet;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itis.services.FilesService;
import ru.itis.model.FileInfo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files")
public class FileDownloadServlet extends HttpServlet {
    private FilesService filesService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.filesService = springContext.getBean(FilesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String storageFileName = request.getParameter("fileName");
            FileInfo fileInfo = filesService.readFileInfo(storageFileName);
            response.setContentType(fileInfo.getType());
            response.setHeader("Content-Disposition", "filename=/" + fileInfo.getOriginalFileName());
            response.setContentLength(fileInfo.getSize().intValue());
            filesService.downloadFileInfo(storageFileName, response.getOutputStream());
        } catch (EmptyResultDataAccessException e) {
            response.setStatus(404);
            response.getWriter().println("File Not Found");
        }
    }
}
