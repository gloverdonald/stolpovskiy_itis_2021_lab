package ru.itis.blog.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.blog.repositories.BlackListRepository;
import ru.itis.blog.security.config.SecurityConfiguration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.blog.security.config.SecurityConfiguration.LOGOUT_FILTER_PROCESSES_URL;

@RequiredArgsConstructor
public class TokenLogoutFilter extends OncePerRequestFilter {

    private final BlackListRepository blackListRepository;

    private final static RequestMatcher logoutRequest =
            new AntPathRequestMatcher(LOGOUT_FILTER_PROCESSES_URL, "GET");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring("Bearer ".length());
                blackListRepository.save(token);
                SecurityContextHolder.clearContext();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
