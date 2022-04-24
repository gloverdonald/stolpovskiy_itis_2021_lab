package ru.itis.blog.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.blog.dto.AuthorRequest;
import ru.itis.blog.models.Author;
import ru.itis.blog.repositories.AuthorsRepository;
import ru.itis.blog.security.details.AuthorUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;


public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String TOKEN = "token";

    public static final String BEARER = "Bearer ";

    private final ObjectMapper objectMapper;

    private final AuthorsRepository authorsRepository;

    private final String secretKey;

    private final Long expirationAccessInMills = 20000L;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, AuthorsRepository authorsRepository, String secretKey) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.authorsRepository = authorsRepository;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthorRequest form = objectMapper.readValue(request.getReader(), AuthorRequest.class);
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
            return super.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthorUserDetails userDetails = (AuthorUserDetails) authResult.getPrincipal();
        Author author = userDetails.getAuthor();

        String token = JWT.create()
                .withSubject(author.getId().toString())
                .withClaim("email", author.getEmail())
                .withClaim("datetime", String.valueOf(Instant.now()))
                .withClaim("role", author.getRole().toString())
                .withExpiresAt(Date.from(Instant.now().plusMillis(expirationAccessInMills)))
                .withClaim("state", author.getState().toString())
                .sign(Algorithm.HMAC256(secretKey));

        author.setToken(token);
        authorsRepository.save(author);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap(TOKEN, BEARER + token));
    }
}
