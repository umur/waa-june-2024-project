package miu.waa.project1.config;


import miu.waa.project1.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static miu.waa.project1.common.Role.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/admin/**").hasAuthority(ADMIN.name())
                .requestMatchers("/api/v1/users/**").hasAnyAuthority(ADMIN.name(), STUDENT.name())
                .requestMatchers("/api/v1/interests/**").hasAnyAuthority(ADMIN.name(), STUDENT.name())
                .requestMatchers("/api/v1/achievements/**").hasAnyAuthority(ADMIN.name(), STUDENT.name())
                .requestMatchers("/api/v1/category/**").hasAnyAuthority(ADMIN.name(), STUDENT.name())
                .requestMatchers("/api/v1/discussion/**").hasAnyAuthority(ADMIN.name(), STUDENT.name())
                .requestMatchers("/api/v1/thread/**").hasAnyAuthority(ADMIN.name(), STUDENT.name())
                    .requestMatchers("/api/v1/events/**").hasAnyAuthority(ADMIN.name(), STUDENT.name())
                .requestMatchers("/resources/**").permitAll() // allow access to resources folder

                .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
