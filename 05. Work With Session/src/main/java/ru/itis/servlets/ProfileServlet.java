package ru.itis.servlets;

import ru.itis.dto.AccountDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDto accountDto = (AccountDto) request.getSession(true).getAttribute("account");
        request.setAttribute("account", accountDto);
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }
}
