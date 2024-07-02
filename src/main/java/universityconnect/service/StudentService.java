package universityconnect.service;

import universityconnect.dto.ProfileDTO;
import universityconnect.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO updateStudent(long id,StudentDTO studentDTO);

    StudentDTO getStudentById(Long id);

    void deleteStudent(long id);

    List<StudentDTO> getStudentsByYear(int year);

    ProfileDTO getProfileByStudentId(long studentId);

    List<StudentDTO> getStudentsByMajor(String major);

    List<StudentDTO> getStudentsByYearAndMajor(int year, String major);
}