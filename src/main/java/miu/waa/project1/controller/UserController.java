package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.dto.UserDTO;
import miu.waa.project1.exception.UserNotFound;
import miu.waa.project1.model.Activity;
import miu.waa.project1.model.Interest;
import miu.waa.project1.model.User;
import miu.waa.project1.service.impl.ActivityServiceImpl;
import miu.waa.project1.service.impl.InterestServiceImpl;
import miu.waa.project1.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final InterestServiceImpl interestService;
    private final ActivityServiceImpl activityService;

    @GetMapping
    public ResponseEntity<Page<User>> searchByMajorEntryYearAndRelevant(
            @RequestParam(required = false) String major,
            @RequestParam(required = false) Integer entryYear,
            @RequestParam(required = false) String keyword,
            Pageable pageable) {
        Page<User> userPage = userService.findByMajorEntryYearAndRelevant(major, entryYear, keyword, pageable);
        return ResponseEntity.ok(userPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update user!");
        }
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or user not found");
        }
        User user = userService.getUserByEmail(userDetails.getUsername());
        if(user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or user not found");
    }

    @GetMapping("/{id}/interests")
    public ResponseEntity<?> getInterestsByUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        List<Interest> interests = user.getInterests();
        return ResponseEntity.ok(interests);
    }

    @PostMapping("/{id}/interests")
    public ResponseEntity<?> createInterestByUser(@PathVariable Long id, @RequestBody Interest interest) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        Interest createdInterest = interestService.createInterestByUser(interest, user);
        return ResponseEntity.ok(createdInterest);
    }

    @PutMapping("/{id}/interests/{interestId}")
    public ResponseEntity<?> updateInterestByUser(@PathVariable Long id, @PathVariable Long interestId,
                                                  @RequestBody Interest interest) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        Interest updatedInterest = interestService.updateInterest(interestId, interest);
        if (updatedInterest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update interest!");
        }
        return ResponseEntity.ok(updatedInterest);
    }

    @DeleteMapping("/{id}/interests/{interestId}")
    public ResponseEntity<?> deleteInterestByUser(@PathVariable Long id, @PathVariable Long interestId) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        interestService.deleteInterest(interestId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<?> getActivitiesByUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        List<Activity> activities = user.getActivities();
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/{id}/activities")
    public ResponseEntity<?> createActivityByUser(@PathVariable Long id, @RequestBody Activity activity) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        Activity createdActivity = activityService.createActivityByUser(activity, user);
        return ResponseEntity.ok(createdActivity);
    }

    @PutMapping("/{id}/activities/{activityId}")
    public ResponseEntity<?> updateActivityByUser(@PathVariable Long id, @PathVariable Long activityId,
                                                  @RequestBody Activity activity) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        Activity updatedActivity = activityService.updateActivity(activityId, activity);
        if (updatedActivity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update activity!");
        }
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{id}/activities/{activityId}")
    public ResponseEntity<?> deleteActivityByUser(@PathVariable Long id, @PathVariable Long activityId) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        activityService.deleteActivity(activityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{id}/report")
    public ResponseEntity<?> reportUser(@PathVariable Long id) {
        try {
            userService.reportUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (UserNotFound ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
