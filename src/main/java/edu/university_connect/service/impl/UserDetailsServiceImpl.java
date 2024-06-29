package edu.university_connect.service.impl;

import edu.university_connect.model.SecurityUser;
import edu.university_connect.model.entity.SystemUser;
import edu.university_connect.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    private final SystemUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> systemUserOpt= repository.findByUsername(username);
        if(systemUserOpt.isPresent()){
            SystemUser user=systemUserOpt.get();
            user.setRoles(user.getRoles());
            return new SecurityUser(systemUserOpt.get());
        }
        else throw new UsernameNotFoundException(username);
    }

}