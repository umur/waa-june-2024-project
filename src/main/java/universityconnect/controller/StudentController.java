package universityconnect.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.ProfileDTO;
import universityconnect.dto.StudentDTO;
import universityconnect.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO student = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{id}/profiles")
    public ResponseEntity<ProfileDTO> getProfileByStudentId(@PathVariable long id) {
        ProfileDTO profileDTO = studentService.getProfileByStudentId(id);
        return ResponseEntity.ok(profileDTO);
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