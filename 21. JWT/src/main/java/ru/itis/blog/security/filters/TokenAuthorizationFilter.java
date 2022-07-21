package ru.itis.blog.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.blog.models.Author;
import ru.itis.blog.repositories.AuthorsRepository;
import ru.itis.blog.repositories.BlackListRepository;
import ru.itis.blog.security.config.SecurityConfiguration;
import ru.itis.blog.security.details.AuthorUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private final AuthorsRepository authorsRepository;

    private final BlackListRepository blackListRepository;

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(SecurityConfiguration.LOGIN_FILTER_PROCESSES_URL)
                || request.getRequestURI().equals(SecurityConfiguration.LOGOUT_FILTER_PROCESSES_URL)) {
            filterChain.doFilter(request, response);
        } else {
            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring("Bearer ".length());

                DecodedJWT jwt = JWT.decode(token);
                if(jwt.getExpiresAt().before(new Date())) {
                    logger.warn("Token is expired (" + jwt.getExpiresAt() + ")");
                    filterChain.doFilter(request, response);
                }

                if (blackListRepository.exists(token)) {
                    logger.warn("Token in blacklist");
                    filterChain.doFilter(request, response);
                }
                Optional<Author> author = authorsRepository.findByToken(token);

                if (author.isPresent()) {
                    AuthorUserDetails userDetails = new AuthorUserDetails(author.get());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(token, null, userDetails.getAuthorities());


                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    logger.warn("Wrong token");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    objectMapper.writeValue(response.getWriter(), Collections.singletonMap("error", "user not found with token"));
                }
            } else {
                logger.warn("Token is missing");
                filterChain.doFilter(request, response);
            }
        }
    }
}

