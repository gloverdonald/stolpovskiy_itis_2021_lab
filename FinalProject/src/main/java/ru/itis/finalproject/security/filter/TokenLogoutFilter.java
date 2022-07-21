package ru.itis.finalproject.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.finalproject.repository.BlackListRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenLogoutFilter extends OncePerRequestFilter {
    public static final String LOGIN_FILTER_PROCESSES_URL = "/sign-in";
    public static final String LOGOUT_FILTER_PROCESSES_URL = "/sign-out";

    private final static RequestMatcher logoutRequest = new AntPathRequestMatcher(LOGOUT_FILTER_PROCESSES_URL, "GET");
    private final BlackListRepository blackListRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("BEARER ")) {
                String token = tokenHeader.substring("BEARER ".length());
                blackListRepository.save(token);
                SecurityContextHolder.clearContext();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
