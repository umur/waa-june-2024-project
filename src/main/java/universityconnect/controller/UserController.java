package universityconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.domain.Report;
import universityconnect.dto.ReportDTO;
import universityconnect.dto.UserDTO;
import universityconnect.dto.UserResponse;
import universityconnect.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reporters")
    public ResponseEntity<List<UserResponse>> getAllReportersByReportedId(@PathVariable long id){
        List<UserResponse> reports = userService.getAllReporterUsersByReportedUserId(id);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}/reporteds")
    public ResponseEntity<List<UserResponse>> getAllReportedsByReporterId(@PathVariable long id){
        List<UserResponse> reports = userService.getAllReportedUsersByReporterUserId(id);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}/blockers")
    public ResponseEntity<List<UserResponse>> getAllBlockersByBlockedId(@PathVariable long id) {
        List<UserResponse> blockedUsers = userService.getAllBlockerUsersByBlockedUserId(id);
        return ResponseEntity.ok(blockedUsers);
    }

    @GetMapping("/{id}/blockeds")
    public ResponseEntity<List<UserResponse>> getAllBlockedsByBlockerId(@PathVariable long id) {
        List<UserResponse> blockingUsers = userService.getAllBlockedUsersByBlockerUserId(id);
        return ResponseEntity.ok(blockingUsers);
    }


}

