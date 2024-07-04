package edu.university_connect.config;

import edu.university_connect.config.jwt.JwtTokenAuthenticationFilter;
import edu.university_connect.config.jwt.JwtTokenProvider;
import edu.university_connect.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtTokenProvider tokenProvider) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/**").permitAll())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/swagger-ui/**").permitAll())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        return authentication -> {

            String username = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";
            SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(username);
            if (!encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("bad.credentials");
            }
            if (!user.isEnabled()) {
                throw new DisabledException("account.not.active");
            }
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        };
    }
}