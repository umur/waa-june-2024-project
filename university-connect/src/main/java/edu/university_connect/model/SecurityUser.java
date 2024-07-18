package edu.university_connect.model;

import edu.university_connect.domain.entity.Role;
import edu.university_connect.domain.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SecurityUser implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;
    private Long id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private User user;

    public SecurityUser(User user) {
        Set<Role> roles = user.getRoles();
        Set<String> actions = roles.stream()
                .flatMap(role -> role.getActions().stream())
                .collect(Collectors.toSet());
        Collection<SimpleGrantedAuthority> directAuthorities = actions.stream()
                .map(SimpleGrantedAuthority::new).toList();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(directAuthorities);
        Collection<SimpleGrantedAuthority> roleList = new ArrayList<>(roles.stream().map(x -> new SimpleGrantedAuthority("ROLE_" + x.getCode())).toList());
        authorities.addAll(roleList);
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = user.isEnabled();
        this.authorities = authorities;
        this.user = user;
    }


}