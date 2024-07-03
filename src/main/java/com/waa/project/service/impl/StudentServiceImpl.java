package com.waa.project.service.impl;

import com.waa.project.contracts.CreateStudentRequest;
import com.waa.project.contracts.StudentResponse;
import com.waa.project.entity.Major;
import com.waa.project.entity.Student;
import com.waa.project.enums.RoleType;
import com.waa.project.exception.ResourceAlreadyExistsException;
import com.waa.project.exception.ResourceNotFoundException;
import com.waa.project.repository.MajorRepository;
import com.waa.project.repository.StudentRepository;
import com.waa.project.repository.UserRepository;
import com.waa.project.service.StudentService;
import com.waa.project.util.MajorErrorMessages;
import com.waa.project.util.StudentErrorMessages;
import com.waa.project.util.UserErrorMessages;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper       modelMapper;
    private final MajorRepository   majorRepository;
    private final PasswordEncoder   passwordEncoder;
    private final UserRepository    userRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            ModelMapper modelMapper,
            MajorRepository majorRepository,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
                             ) {
        this.studentRepository = studentRepository;
        this.modelMapper       = modelMapper;
        this.majorRepository   = majorRepository;
        this.passwordEncoder   = passwordEncoder;
        this.userRepository    = userRepository;
    }

    @Override
    public Page<StudentResponse> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable)
                                .map(student -> modelMapper.map(student, StudentResponse.class));
    }

    @Override
    public StudentResponse findById(Long id) {
        return studentRepository.findById(id)
                                .map(student -> modelMapper.map(student, StudentResponse.class))
                                .orElseThrow(
                                        () -> new ResourceNotFoundException(StudentErrorMessages.studentNotFound(id)));
    }

    @Transactional
    @Override
    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = modelMapper.map(createStudentRequest, Student.class);

        checkIfUsernameExists(createStudentRequest.getUsername());
        checkIfEmailExists(createStudentRequest.getEmail());

        Major major = majorRepository.findById(createStudentRequest.getMajorId())
                                     .orElseThrow(() -> new ResourceNotFoundException(
                                             MajorErrorMessages.majorrNotFound(createStudentRequest.getMajorId())));

        student.setMajor(major);

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRoleType(RoleType.STUDENT);

        return modelMapper.map(studentRepository.save(student), StudentResponse.class);
    }

    private void checkIfUsernameExists(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new ResourceAlreadyExistsException(UserErrorMessages.usernameAlreadyExists(username));
        });
    }

    private void checkIfEmailExists(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new ResourceAlreadyExistsException(UserErrorMessages.emailAlreadyExists(email));
        });
    }
}
