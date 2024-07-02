package universityconnect.service;

import universityconnect.domain.User;
import universityconnect.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    User findById(Long userId);

    List<UserDTO> getAllReportedUsersByReporterUserId(Long id);

    List<UserDTO> getAllReporterUsersByReportedUserId(Long id);

    List<UserDTO> getAllBlockedUsersByBlockerUserId(Long id);

    List<UserDTO> getAllBlockerUsersByBlockedUserId(Long id);


}

