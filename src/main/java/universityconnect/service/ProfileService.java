package universityconnect.service;

import universityconnect.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {

    ProfileDTO createProfile(ProfileDTO profileDTO);

    List<ProfileDTO> getAllProfiles();

    ProfileDTO getProfileById(long id);

    void deleteProfile(long id);

    ProfileDTO updateProfile(long id, ProfileDTO profileDTO);

    ProfileDTO getProfileByUserId(long userId);
}