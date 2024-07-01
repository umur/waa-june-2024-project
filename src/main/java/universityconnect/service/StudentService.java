package universityconnect.service;

import universityconnect.dto.ProfileDTO;
import universityconnect.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(long id);

    StudentDTO updateStudent(long id,StudentDTO studentDTO);

    void deleteStudent(long id);

    ProfileDTO getProfileByStudentId(long studentId);

}
