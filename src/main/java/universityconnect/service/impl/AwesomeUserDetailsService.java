package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import universityconnect.domain.User;
import universityconnect.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AwesomeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;
    private static final Logger logger = LoggerFactory.getLogger(AwesomeUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user by username: {}", username);
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> {
                    logger.error("User not found with username: {}", username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });
        logger.debug("User found: {}", user);
        return new AwesomeUserDetails(user);
    }
}
