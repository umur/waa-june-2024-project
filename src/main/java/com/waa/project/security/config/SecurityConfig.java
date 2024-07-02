package com.waa.project.security.config;

import com.waa.project.enums.RoleType;
import com.waa.project.security.filter.JwtTokenFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                   .cors(AbstractHttpConfigurer::disable)
                   .sessionManagement(
                           sessionMgr -> sessionMgr.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   .authorizeHttpRequests(
                           authorizeRequests -> authorizeRequests.requestMatchers("/api/v1/admins/**")
                                                                 .hasRole(RoleType.ADMIN.name())
                                                                 .requestMatchers("/api/v1/students/**")
                                                                 .hasRole(RoleType.STUDENT.name())
                                                                 .anyRequest()
                                                                 .permitAll()
                                         )
                   .exceptionHandling(
                           exceptionHandler -> exceptionHandler.authenticationEntryPoint(
                                   (request, response, authException) -> response.sendError(
                                           HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())
                                                                                        )
                                     )
                   .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                   .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
