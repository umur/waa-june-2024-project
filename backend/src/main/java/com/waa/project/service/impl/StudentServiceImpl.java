package com.waa.project.service.impl;

import com.waa.project.contracts.CreateStudentRequest;
import com.waa.project.contracts.StudentResponse;
import com.waa.project.contracts.UpdateStudentProfileRequest;
import com.waa.project.entity.Major;
import com.waa.project.entity.Student;
import com.waa.project.enums.RoleType;
import com.waa.project.exception.ResourceAlreadyExistsException;
import com.waa.project.exception.ResourceNotFoundException;
import com.waa.project.repository.MajorRepository;
import com.waa.project.repository.StudentRepository;
import com.waa.project.repository.UserRepository;
import com.waa.project.service.FileService;
import com.waa.project.service.StudentService;
import com.waa.project.util.FileSaveLocations;
import com.waa.project.util.MajorErrorMessages;
import com.waa.project.util.StudentErrorMessages;
import com.waa.project.util.UserErrorMessages;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper       modelMapper;
    private final MajorRepository   majorRepository;
    private final PasswordEncoder   passwordEncoder;
    private final UserRepository    userRepository;
    private final FileService       fileService;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            ModelMapper modelMapper,
            MajorRepository majorRepository,
            PasswordEncoder passwordEncoder, UserRepository userRepository, FileService fileService
                             ) {
        this.studentRepository = studentRepository;
        this.modelMapper       = modelMapper;
        this.majorRepository   = majorRepository;
        this.passwordEncoder   = passwordEncoder;
        this.userRepository    = userRepository;
        this.fileService       = fileService;
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

    @Transactional
    @Override
    public StudentResponse updateStudentProfile(
            String username, UpdateStudentProfileRequest updateStudentProfileRequest, MultipartFile picture
                                               ) {
        Student student = studentRepository.findByUsername(username)
                                           .orElseThrow(() -> new ResourceNotFoundException(
                                                   StudentErrorMessages.studentNotFound(username)));

        modelMapper.map(updateStudentProfileRequest, student);


//        student.setPassword(passwordEncoder.encode(updateStudentProfileRequest.getPassword()));

        if (picture != null && !picture.isEmpty()) {
            String picturePath = fileService.saveFile(picture, FileSaveLocations.studentProfile(username));

            student.setPicture(picturePath);
        }

        return modelMapper.map(studentRepository.save(student), StudentResponse.class);
    }

    @Override
    public Page<StudentResponse> searchStudents(String text, Pageable pageable) {
        return studentRepository.searchByText(text, pageable)
                                .map(student -> modelMapper.map(student, StudentResponse.class));
    }

    @Override
    public StudentResponse findByUsername(String username) {
        return studentRepository.findByUsername(username)
                                .map(student -> modelMapper.map(student, StudentResponse.class))
                                .orElseThrow(
                                        () -> new ResourceNotFoundException(StudentErrorMessages.studentNotFound(username)));

    }

    @Transactional
    @Override
    public void deleteStudentByUsername(String username) {
        Student student = studentRepository.findByUsername(username).orElseThrow(
        ()-> new ResourceNotFoundException(StudentErrorMessages.studentNotFound(username)));

        studentRepository.delete(student);
    }
}
