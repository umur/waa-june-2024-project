package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityconnect.domain.Profile;
import universityconnect.dto.ProfileDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.ProfileMapper;
import universityconnect.repository.ProfileRepository;
import universityconnect.service.ProfileService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = profileMapper.profileDTOToProfile(profileDTO);
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.profileToProfileDTO(savedProfile);
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profileMapper::profileToProfileDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDTO getProfileById(long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile Not Found with ID: " + id));
        return profileMapper.profileToProfileDTO(profile);
    }

    @Override
    public void deleteProfile(long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile Not Found with ID: " + id));
        profileRepository.delete(profile);
    }

    @Override
    public ProfileDTO updateProfile(long id, ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile Not Found with ID: " + id));

        profile.setActivities(profileDTO.getActivities());
        profile.setAchievements(profileDTO.getAchievements());
        profile.setInterests(profileDTO.getInterests());
        profileRepository.save(profile);
        return profileMapper.profileToProfileDTO(profile);
    }
}
