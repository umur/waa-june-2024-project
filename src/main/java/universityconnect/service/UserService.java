package universityconnect.service;

import universityconnect.domain.User;
import universityconnect.dto.UserDTO;
import universityconnect.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    User findById(Long userId);

    List<UserResponse> getAllReportedUsersByReporterUserId(Long id);

    List<UserResponse> getAllReporterUsersByReportedUserId(Long id);

    List<UserResponse> getAllBlockedUsersByBlockerUserId(Long id);

    List<UserResponse> getAllBlockerUsersByBlockedUserId(Long id);


}

