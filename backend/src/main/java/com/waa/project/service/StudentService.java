package com.waa.project.service;

import com.waa.project.contracts.CreateStudentRequest;
import com.waa.project.contracts.StudentResponse;
import com.waa.project.contracts.UpdateStudentProfileRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    Page<StudentResponse> findAll(Pageable pageable);

    StudentResponse findById(Long id);    StudentResponse findByUsername(String username);

    StudentResponse createStudent(CreateStudentRequest createStudentRequest);

    StudentResponse updateStudentProfile(
            String username,
            UpdateStudentProfileRequest updateStudentProfileRequest,
            MultipartFile picture
                                        );

    Page<StudentResponse> searchStudents(String text, Pageable pageable);

    void deleteStudentByUsername(String username);
}
