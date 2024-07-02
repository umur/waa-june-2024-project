package com.waa.project.security.service;

import com.waa.project.enums.RoleType;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestUserDetailsService implements UserDetailsService {

    private final InMemoryUserDetailsManager userDetailsManager;

    public TestUserDetailsService() {
        userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withUsername("admin")
                    .password("{noop}admin")
                    .roles(RoleType.ADMIN.name())
                    .build()
                                     );

        userDetailsManager.createUser(
                User.withUsername("student")
                    .password("{noop}student")
                    .roles(RoleType.STUDENT.name())
                    .build()
                                     );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsManager.loadUserByUsername(username);
    }
}
