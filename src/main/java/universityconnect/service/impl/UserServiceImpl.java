package universityconnect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityconnect.domain.*;
import universityconnect.dto.UserDTO;
import universityconnect.dto.UserResponse;
import universityconnect.exception.EmailAlreadyExistsException;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.UserMapper;
import universityconnect.mapper.UserResponseMapper;
import universityconnect.repository.*;
import universityconnect.service.UserService;
import universityconnect.util.PasswordEncoderUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserResponseMapper userResponseMapper;

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

    private AuditData updateDefaultAuditData(AuditData auditData) {
        auditData.setUpdatedBy("ADMIN");
        auditData.setUpdatedOn(LocalDate.now());
        return auditData;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));
        return userResponseMapper.userToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setPassword(passwordEncoderUtil.encodePassword(userDTO.getPassword()));
        existingUser.setRole(userDTO.getRole());

        // Use ID from path variable in the switch case
        switch (existingUser.getRole().name()) {
            case "STUDENT" -> updateStudent(id, userDTO);
            case "ADMIN" -> updateAdmin(id, userDTO);
            default -> throw new IllegalArgumentException("Unsupported role: " + userDTO.getRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    private void updateStudent(Long id, UserDTO userDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Not Found with ID: " + id));

        existingStudent.setMajor(userDTO.getMajor());
        existingStudent.setYear(userDTO.getYear());
        existingStudent.setAuditData(updateDefaultAuditData(existingStudent.getAuditData()));

        studentRepository.save(existingStudent);
    }

    private void updateAdmin(Long id, UserDTO userDTO) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin Not Found with ID: " + id));

        existingAdmin.setDepartment(userDTO.getDepartment());
        existingAdmin.setAuditData(updateDefaultAuditData(existingAdmin.getAuditData()));

        adminRepository.save(existingAdmin);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));
        Profile profile = profileRepository.findByUserId(id);
        profileRepository.delete(profile);
        userRepository.delete(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + userId));
    }

    @Override
    public List<UserResponse> getAllReportedUsersByReporterUserId(Long id) {
        List<User> reportedUsers = reportRepository.findByReportedUserByReporterUserId(id);
        return reportedUsers.stream()
                .map(userResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getAllReporterUsersByReportedUserId(Long id) {
        List<User> reporterUsers = reportRepository.findByReporterUserByReportedUserId(id);
        return reporterUsers.stream()
                .map(userResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getAllBlockedUsersByBlockerUserId(Long id) {
        List<User> blockedUsers = blockRepository.findBlockedUsersByBlockerUserId(id);
        return blockedUsers.stream()
                .map(userResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getAllBlockerUsersByBlockedUserId(Long id) {
        List<User> blockingUsers = blockRepository.findBlockerUsersByBlockedUserId(id);
        return blockingUsers.stream()
                .map(userResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

}
