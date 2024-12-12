package edu.university_connect.config;

import edu.university_connect.model.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ContextUser {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public SecurityUser getLoginUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public SecurityUser getUser() {
        return getLoginUser();
    }

    public boolean hasRole(String roleCode) {
        return getLoginUser().getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_" + roleCode));
    }

    public boolean isNormalUser() {
        return hasRole("normal-user");
    }
}