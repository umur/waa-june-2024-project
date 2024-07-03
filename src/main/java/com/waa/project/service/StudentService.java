package com.waa.project.service;

import com.waa.project.contracts.CreateStudentRequest;
import com.waa.project.contracts.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<StudentResponse> findAll(Pageable pageable);

    StudentResponse findById(Long id);

    StudentResponse createStudent(CreateStudentRequest createStudentRequest);
}
