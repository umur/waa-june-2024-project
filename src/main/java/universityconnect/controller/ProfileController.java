package universityconnect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.ProfileDTO;
import universityconnect.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO){
         ProfileDTO createdProfile = profileService.createProfile(profileDTO);
         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(){
        List<ProfileDTO> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable long id){
        ProfileDTO profile = profileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable long id){
        profileService.deleteProfile(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable long id, @RequestBody ProfileDTO profileDTO){
        ProfileDTO profile = profileService.updateProfile(id,profileDTO);
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

}
