package com.waa.project.controller;

import com.waa.project.contracts.CreateStudentRequest;
import com.waa.project.contracts.StudentResponse;
import com.waa.project.contracts.UpdateStudentProfileRequest;
import com.waa.project.security.exception.ActionForbiddenException;
import com.waa.project.security.util.AuthErrorMessages;
import com.waa.project.service.StudentService;
import com.waa.project.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService  studentService;
    private final UserServiceImpl userServiceImpl;

    public StudentController(StudentService studentService, UserServiceImpl userServiceImpl) {
        this.studentService  = studentService;
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping({"/students", "/admins/students"})
    public ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.findAll(pageable));
    }

    @GetMapping({"/students/{id}", "/admins/students/{id}"})
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Long id, Pageable pageable
                                                         ) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping("/student/register")
    public ResponseEntity<StudentResponse> registerStudent(
            @Valid @RequestBody CreateStudentRequest createStudentRequest
                                                          ) {
        return new ResponseEntity<>(studentService.createStudent(createStudentRequest), HttpStatus.CREATED);

    }

    @PostMapping("/students/{username}/profile")
    public ResponseEntity<StudentResponse> updateStudentProfile(
            @RequestPart(value = "profile", required = false) @Valid UpdateStudentProfileRequest updateProfileRequest,
            @RequestPart(value = "picture", required = false) MultipartFile picture,
            @AuthenticationPrincipal User user,
            @PathVariable("username") String username
                                                               ) {
        if (!username.equals(user.getUsername())) {
            throw new ActionForbiddenException(AuthErrorMessages.forbidden());
        }

        return ResponseEntity.ok(studentService.updateStudentProfile(username, updateProfileRequest, picture));
    }
}
