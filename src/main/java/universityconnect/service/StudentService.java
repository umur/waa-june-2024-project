package universityconnect.service;

import universityconnect.dto.ProfileDTO;
import universityconnect.dto.StudentDTO;
import universityconnect.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllStudents();

    StudentDTO updateStudent(long id,StudentDTO studentDTO);

    StudentResponse getStudentById(Long id);

    void deleteStudent(long id);

    List<StudentResponse> getStudentsByYear(int year);

    ProfileDTO getProfileByStudentId(long studentId);

    List<StudentResponse> getStudentsByMajor(String major);

    List<StudentResponse> getStudentsByYearAndMajor(int year, String major);
}