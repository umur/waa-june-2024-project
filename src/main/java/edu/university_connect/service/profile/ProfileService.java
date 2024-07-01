package edu.university_connect.service.profile;

import edu.university_connect.domain.entity.Profile;

import java.util.Optional;

public interface ProfileService {

    Optional<Profile> getProfileByUserId(Long id);

    Profile saveUserProfile(Profile profile);
}