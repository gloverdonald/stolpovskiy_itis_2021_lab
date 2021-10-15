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
import java.util.List;


@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet {
    private AccountsRepository accountsRepository;
    public static final String COOKIE_AUTHENTICATION = "AUTHENTICATION_STATUS";

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.accountsRepository = springContext.getBean(AccountsRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(COOKIE_AUTHENTICATION)) {
                if (cookie.getValue().equals("user_is_authenticated")) {
                    List<Account> accounts = accountsRepository.findAll();
                    request.setAttribute("accounts", accounts);
                    request.getRequestDispatcher("/jsp/accounts.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }
}
