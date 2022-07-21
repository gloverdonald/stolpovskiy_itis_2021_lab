package ru.itis.blog.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.blog.repositories.AuthorsRepository;
import ru.itis.blog.repositories.BlackListRepository;
import ru.itis.blog.security.filters.TokenAuthenticationFilter;
import ru.itis.blog.security.filters.TokenAuthorizationFilter;
import ru.itis.blog.security.filters.TokenLogoutFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_FILTER_PROCESSES_URL = "/sign-in";

    public static final String LOGOUT_FILTER_PROCESSES_URL = "/sign-out";

    private static final String[] IGNORE = {
            "/account-swagger/api-docs",
            "/swagger-ui.html",
            "/db/**",
    };

    @Value("${jwt.secretKey}")
    private String secretKey;

    private final PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper;

    private final UserDetailsService accountUserDetailsService;

    private final AuthorsRepository authorsRepository;

    private final BlackListRepository blackListRepository;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter =
                new TokenAuthenticationFilter(authenticationManagerBean(), objectMapper, authorsRepository, secretKey);
        tokenAuthenticationFilter.setFilterProcessesUrl(LOGIN_FILTER_PROCESSES_URL);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(tokenAuthenticationFilter);
        http.addFilterBefore(new TokenAuthorizationFilter(authorsRepository, blackListRepository, objectMapper),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new TokenLogoutFilter(blackListRepository), TokenAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/authors/sign-up").permitAll()
                .antMatchers(LOGIN_FILTER_PROCESSES_URL).permitAll()
                .antMatchers(LOGOUT_FILTER_PROCESSES_URL).hasAnyAuthority()
                .antMatchers("/authors/**").authenticated();
    }
}
