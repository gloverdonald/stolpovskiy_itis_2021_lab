package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.services.FileScanner;
import ru.itis.services.WordProcessor;
import ru.itis.repositories.WordRepository;
import ru.itis.config.ApplicationConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class WordsServlet extends HttpServlet {
    private WordProcessor wordProcessor;

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        springContext.getBean(WordRepository.class);
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        this.wordProcessor = context.getBean(WordProcessor.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String folder = request.getParameter("folder");
        FileScanner fileScanner = new FileScanner(folder);
        List<File> paths = fileScanner.getFiles();
        wordProcessor.getInfo(fileScanner);
        TreeMap<String, TreeMap<String, Integer>> tops_word = new TreeMap<>();
        for (File path : paths) {
            String p = path + "";
            tops_word.put(p, wordProcessor.getTopTenInTable(p));
        }
        request.setAttribute("word", tops_word);
        request.getRequestDispatcher("jsp/words.jsp").forward(request, response);
    }
}
