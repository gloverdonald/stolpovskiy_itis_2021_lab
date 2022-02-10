package ru.itis.servlets;

import ru.itis.FileScanner;
import ru.itis.WordProcessor;
import javax.servlet.http.*;
import java.io.IOException;

public class WordsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String folder = request.getParameter("folder");
        FileScanner fileScanner = new FileScanner(folder);
        WordProcessor wordProcessor = new WordProcessor(fileScanner);
        response.getWriter().println(wordProcessor.getInfo());
    }
}
