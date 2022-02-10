package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    public static final String COOKIE_AUTHENTICATION = "AUTHENTICATION_STATUS";
    private AccountsRepository accountsRepository;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.accountsRepository = springContext.getBean(AccountsRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String correctPassword;
        Account account;
        try {
            account = accountsRepository.findUser(username).get();
            correctPassword = account.getPassword();
        } catch (NoSuchElementException e) {
            correctPassword = null;
        }

        if (password.equals(correctPassword)) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_AUTHENTICATION)) {
                    cookie.setValue("user_is_authenticated");
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/sign-in");
        }
    }
}