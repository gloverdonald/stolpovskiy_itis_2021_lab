package ru.itis.finalproject.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.itis.finalproject.dto.response.UserResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        return loadUserDetails((UserResponse) token.getPrincipal(), String.valueOf(token.getCredentials()));
    }

    private UserDetails loadUserDetails(UserResponse userResponse, String token) {
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userResponse.getRole().toString()));
        return UserDetailsImpl.builder()
                .id(userResponse.getId())
                .username(userResponse.getEmail())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .authorities(authorities)
                .token(token)
                .build();
    }
}
