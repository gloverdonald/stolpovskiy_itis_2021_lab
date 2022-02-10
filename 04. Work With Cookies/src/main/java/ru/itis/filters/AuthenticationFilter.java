package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public static final String COOKIE_AUTHENTICATION = "AUTHENTICATION_STATUS";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean authCont = false;

        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_AUTHENTICATION)) {
                    authCont = true;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("no have cookies");
        }

        if (!authCont) {
            Cookie colorCookie = new Cookie(COOKIE_AUTHENTICATION, "user_is_not_authenticated");
            colorCookie.setMaxAge(60);
            response.addCookie(colorCookie);
        }
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
