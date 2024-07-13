package edu.university_connect.service.student;

import edu.university_connect.domain.entity.Student;
import edu.university_connect.model.contract.dto.StudentDto;
import edu.university_connect.model.contract.request.student.StudentCreateRequest;
import edu.university_connect.model.contract.request.student.StudentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> getByEmail(String email);

    List<StudentDto> getAll();

    Page<StudentDto> getPage(Pageable pageable);

    StudentDto create(StudentCreateRequest createRequest);

    StudentDto getById(Long id);

    StudentDto update(Long id, StudentUpdateRequest updateRequest);

    Optional<Student> getStudentById(Long id);

    boolean delete(Long id);

    Optional<Student> getStudentByEmail(String email);

    Student saveStudent(Student student);
}
