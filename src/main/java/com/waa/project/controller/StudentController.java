package com.waa.project.controller;

import com.waa.project.contracts.CreateStudentRequest;
import com.waa.project.contracts.StudentResponse;
import com.waa.project.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
}
