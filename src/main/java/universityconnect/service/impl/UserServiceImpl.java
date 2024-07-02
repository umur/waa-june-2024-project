package universityconnect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityconnect.domain.*;
import universityconnect.dto.BlockDTO;
import universityconnect.dto.ReportDTO;
import universityconnect.dto.UserDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.BlockMapper;
import universityconnect.mapper.ReportMapper;
import universityconnect.mapper.StudentMapper;
import universityconnect.mapper.UserMapper;
import universityconnect.repository.*;
import universityconnect.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);

        // Automatically create a student if the role is STUDENT
        if (userDTO.getRole().name().equals("STUDENT")) {
            Student student = new Student(userDTO.getYear(),userDTO.getMajor());
            student.setUsername(userDTO.getUsername());
            student.setEmail(userDTO.getEmail());
            student.setPassword(userDTO.getPassword());
            student.setRole(userDTO.getRole());
            student.setAddress(userDTO.getAddress());
            studentRepository.saveAndFlush(student);
            Profile profile = new Profile();
            profile.setUser(student);
            profileRepository.save(profile);
            user.setId(student.getId());
        } else if (userDTO.getRole().name().equals("ADMIN")) {
            Admin admin = new Admin(userDTO.getDepartment());
            admin.setUsername(userDTO.getUsername());
            admin.setEmail(userDTO.getEmail());
            admin.setAddress(userDTO.getAddress());
            admin.setRole(userDTO.getRole());
            admin.setPassword(userDTO.getPassword());
            adminRepository.saveAndFlush(admin);
            Profile profile = new Profile();
            profile.setUser(admin);
            profileRepository.save(profile);
            user.setId(admin.getId());
        }
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));
        return userMapper.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));
        userRepository.delete(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + userId));
    }

    @Override
    public List<UserDTO> getAllReportedUsersByReporterUserId(Long id) {
        List<User> reportedUsers = reportRepository.findByReportedUserByReporterUserId(id);
        return reportedUsers.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllReporterUsersByReportedUserId(Long id) {
        List<User> reporterUsers = reportRepository.findByReporterUserByReportedUserId(id);
        return reporterUsers.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
     }

    @Override
    public List<UserDTO> getAllBlockedUsersByBlockerUserId(Long id) {
        List<User> blockedUsers = blockRepository.findBlockedUsersByBlockerUserId(id);
        return blockedUsers.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllBlockerUsersByBlockedUserId(Long id) {
        List<User> blockingUsers = blockRepository.findBlockerUsersByBlockedUserId(id);
        return blockingUsers.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

}
