package universityconnect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityconnect.domain.*;
import universityconnect.dto.UserDTO;
import universityconnect.exception.EmailAlreadyExistsException;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.StudentMapper;
import universityconnect.mapper.UserMapper;
import universityconnect.repository.*;
import universityconnect.service.UserService;
import universityconnect.util.PasswordEncoderUtil;

import java.time.LocalDate;
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
    private PasswordEncoderUtil passwordEncoderUtil;

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        User user = switch (userDTO.getRole().name()) {
            case "STUDENT" -> createStudent(userDTO);
            case "ADMIN" -> createAdmin(userDTO);
            default -> throw new IllegalArgumentException("Unsupported role: " + userDTO.getRole());
        };
        return userMapper.userToUserDTO(user);
    }

    private User createStudent(UserDTO userDTO) {
        Student student = new Student(userDTO.getYear(), userDTO.getMajor());
        setCommonUserFields(student, userDTO);
        student.setAuditData(createDefaultAuditData());

        studentRepository.save(student);

        Profile profile = new Profile();
        profile.setUser(student);
        profileRepository.save(profile);

        return student;
    }

    private User createAdmin(UserDTO userDTO) {
        Admin admin = new Admin(userDTO.getDepartment());
        setCommonUserFields(admin, userDTO);
        admin.setAuditData(createDefaultAuditData());

        adminRepository.save(admin);

        Profile profile = new Profile();
        profile.setUser(admin);
        profileRepository.save(profile);

        return admin;
    }

    private void setCommonUserFields(User user, UserDTO userDTO) {
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setRole(userDTO.getRole());
        user.setPassword(passwordEncoderUtil.encodePassword(userDTO.getPassword()));
    }

    private AuditData createDefaultAuditData() {
        AuditData auditData = new AuditData();
        auditData.setCreatedBy("ADMIN");
        auditData.setCreatedOn(LocalDate.now());
        return auditData;
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
