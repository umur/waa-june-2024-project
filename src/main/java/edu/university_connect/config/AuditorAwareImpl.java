package edu.university_connect.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    final
    ContextUser contextUser;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(contextUser.getUser().getUsername());
    }
}
