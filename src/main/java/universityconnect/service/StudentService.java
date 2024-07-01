package universityconnect.service;

import universityconnect.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    List<StudentDTO> getStudentsByYear(int year);

    List<StudentDTO> getStudentsByMajor(String major);

    List<StudentDTO> getStudentsByYearAndMajor(int year, String major);
}