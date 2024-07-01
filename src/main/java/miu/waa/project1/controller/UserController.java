package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.dto.UserDTO;
import miu.waa.project1.exception.UserNotFound;
import miu.waa.project1.model.Interest;
import miu.waa.project1.model.User;
import miu.waa.project1.service.impl.InterestServiceImpl;
import miu.waa.project1.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    private final InterestServiceImpl interestService;

    @GetMapping("/search")
    public List<User> searchByMajorEntryYearAndRelevant(
            @RequestParam(required = false) String major,
            @RequestParam(required = false) Integer entryYear,
            @RequestParam(required = false) String keyword) {
        return userService.findByMajorEntryYearAndRelevant(major, entryYear, keyword);
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
    public ResponseEntity<?> getInterestsByUserId(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        List<Interest> interests = user.getInterests();
        return ResponseEntity.ok(interests);
    }

    @PostMapping("/{id}/interests")
    public ResponseEntity<?> createFavoriteProperty(@PathVariable Long id, @RequestBody Interest interest) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        Interest createdInterest = interestService.createInterestByUser(interest, user);
        return ResponseEntity.ok(createdInterest);
    }

    @DeleteMapping("/{id}/interests/{interestId}")
    public ResponseEntity<?> deleteInterestByUser(@PathVariable Long id, @PathVariable Long interestId) {
        User user = userService.getUserById(id);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");

        interestService.deleteInterest(id);
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
}
