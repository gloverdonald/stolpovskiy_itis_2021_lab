package ru.itis.finalproject.security.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.itis.finalproject.repository.BlackListRepository;
import ru.itis.finalproject.security.details.UserDetailsServiceImpl;
import ru.itis.finalproject.security.filter.TokenAuthorizationFilter;
import ru.itis.finalproject.security.filter.TokenLogoutFilter;
import ru.itis.finalproject.service.impl.TokenAuthorizationServiceImpl;

import java.util.Collections;

import static ru.itis.finalproject.security.filter.TokenLogoutFilter.LOGOUT_FILTER_PROCESSES_URL;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:application.properties")
@SecurityScheme(name = "authorization", scheme = "bearer", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PERMIT_ALL = {
            "/user/sign-up",
            "/user/sign-in",
            "/user/refresh",
            "/user/confirm/**",
            "/swagger-ui/**",
            "/v3/api-docs/",
            "/swagger-ui.html"
    };

    private static final String[] IGNORE = {
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    private final UserDetailsServiceImpl userDetailsService;
    private final TokenAuthorizationServiceImpl tokenAuthorizationService;
    private final BlackListRepository blackListRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(IGNORE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers(PERMIT_ALL).permitAll()
                .antMatchers(LOGOUT_FILTER_PROCESSES_URL).hasAnyAuthority()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(tokenLogoutFilter(), TokenAuthorizationFilter.class)
                .authenticationProvider(authenticationProvider());
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(userDetailsService);
        authenticationProvider.setThrowExceptionWhenTokenRejected(false);
        return authenticationProvider;
    }

    @Override
    protected AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    public TokenAuthorizationFilter tokenAuthorizationFilter() {
        return new TokenAuthorizationFilter(tokenAuthorizationService, blackListRepository);
    }

    public TokenLogoutFilter tokenLogoutFilter() {
        return new TokenLogoutFilter(blackListRepository);
    }
}
