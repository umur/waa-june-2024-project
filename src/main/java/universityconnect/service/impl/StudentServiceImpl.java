package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityconnect.domain.Student;
import universityconnect.dto.StudentDTO;
import universityconnect.mapper.StudentMapper;
import universityconnect.repository.StudentRepository;
import universityconnect.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private  StudentRepository studentRepository;

    @Autowired
    private  StudentMapper studentMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student != null ? studentMapper.studentToStudentDTO(student) : null;
    }

    @Override
    public List<StudentDTO> getStudentsByYear(int year) {
        return studentRepository.findByYear(year).stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major).stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByYearAndMajor(int year, String major) {
        return studentRepository.findByYearAndMajor(year, major).stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }
}