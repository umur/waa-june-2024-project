package edu.university_connect.service.impl;

import edu.university_connect.model.SecurityUser;
import edu.university_connect.model.entity.User;
import edu.university_connect.repository.UserRepository;
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

    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt= repository.findByUsername(username);
        if(userOpt.isPresent()){
            User user=userOpt.get();
            user.setRoles(user.getRoles());
            return new SecurityUser(userOpt.get());
        }
        else throw new UsernameNotFoundException(username);
    }

}