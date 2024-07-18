package edu.university_connect.service.profile;

import edu.university_connect.domain.entity.Profile;
import edu.university_connect.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;

    @Override
    public Optional<Profile> getProfileByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public Profile saveUserProfile(Profile profile) {
        return repository.save(profile);
    }
}
