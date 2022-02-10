package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ColorFilter implements Filter {

    private final static String COLOR_PARAMETER_NAME = "color";

    private final static String COLOR_ATTRIBUTE_NAME = "color";

    private final static String PAGE_COLOR_COOKIE_NAME = "pageColor";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String color = request.getParameter(COLOR_PARAMETER_NAME);
        if (color != null) {
            createColorCookie(response, color);
        } else {
            color = processColorCookie(request);
        }
        request.setAttribute(COLOR_ATTRIBUTE_NAME, color);
        chain.doFilter(request, response);
    }

    private String processColorCookie(HttpServletRequest request) {
        String color = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(PAGE_COLOR_COOKIE_NAME)) {
                color = cookie.getValue();
            }
        }
        return color;
    }

    private void createColorCookie(HttpServletResponse response, String color) {
        Cookie colorCookie = new Cookie(PAGE_COLOR_COOKIE_NAME, color);
        response.addCookie(colorCookie);
    }

    @Override
    public void destroy() {

    }
}