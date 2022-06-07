package ru.itis.finalproject.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.finalproject.dto.response.UserResponse;
import ru.itis.finalproject.exceptions.UnauthorizedException;
import ru.itis.finalproject.repository.BlackListRepository;
import ru.itis.finalproject.service.TokenAuthorizationService;
import ru.itis.finalproject.util.HttpResponseUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class TokenAuthorizationFilter extends GenericFilterBean {
    private final TokenAuthorizationService tokenAuthorizationService;
    private final BlackListRepository blackListRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        try {
            String token = parseToken((HttpServletRequest) request);
            if (Objects.nonNull(token)) {
                if (blackListRepository.exists(token)) {
                    throw new UnauthorizedException("Access Token In Black List");
                }
                UserResponse userResponse = tokenAuthorizationService.getUserInfoByToken(token);
                PreAuthenticatedAuthenticationToken authenticationToken =
                        new PreAuthenticatedAuthenticationToken(userResponse, token);
                if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else if (!SecurityContextHolder.getContext().getAuthentication().getCredentials().equals(token)) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            chain.doFilter(request, response);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            HttpResponseUtil.putExceptionInResponse(((HttpServletRequest) request), ((HttpServletResponse) response),
                    exception, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String parseToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("AUTHORIZATION");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("BEARER ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
