package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().startsWith("/resources")) {
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession(false);
        boolean isAuthenticated = false;
        boolean sessionExists = session != null;
        boolean isRequestOnAuthPage = request.getRequestURI().equals("/sign-in") ||
                request.getRequestURI().equals("/sign-up");
        if (sessionExists) {
            isAuthenticated = session.getAttribute("account") != null;
        }
        if (isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage) {
            filterChain.doFilter(request, response);
        } else if (isAuthenticated) {
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    public void destroy() {
    }
}

