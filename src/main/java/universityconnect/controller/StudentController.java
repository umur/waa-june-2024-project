package universityconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import universityconnect.dto.StudentDTO;
import universityconnect.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/year/{year}")
    public List<StudentDTO> getStudentsByYear(@PathVariable int year) {
        return studentService.getStudentsByYear(year);
    }

    @GetMapping("/major/{major}")
    public List<StudentDTO> getStudentsByMajor(@PathVariable String major) {
        return studentService.getStudentsByMajor(major);
    }

    @GetMapping("/year/{year}/major/{major}")
    public List<StudentDTO> getStudentsByYearAndMajor(@PathVariable int year, @PathVariable String major) {
        return studentService.getStudentsByYearAndMajor(year, major);
    }
}