package universityconnect.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.ProfileDTO;
import universityconnect.dto.StudentDTO;
import universityconnect.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(@RequestParam(name = "year", required = false) Integer year,
                                                           @RequestParam(name = "major", required = false) String major) {
        if (year != null && major != null) {
            List<StudentDTO> students = studentService.getStudentsByYearAndMajor(year, major);
            return ResponseEntity.ok(students);
        } else if (year != null) {
            List<StudentDTO> students = studentService.getStudentsByYear(year);
            return ResponseEntity.ok(students);
        } else if (major != null) {
            List<StudentDTO> students = studentService.getStudentsByMajor(major);
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.ok(studentService.getAllStudents());

    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDTO) {
//        StudentDTO student = studentService.updateStudent(id, studentDTO);
//        return ResponseEntity.ok(student);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteStudent(@PathVariable long id) {
//        studentService.deleteStudent(id);
//    }

    @GetMapping("/{id}/profiles")
    public ResponseEntity<ProfileDTO> getProfileByStudentId(@PathVariable long id) {
        ProfileDTO profileDTO = studentService.getProfileByStudentId(id);
        return ResponseEntity.ok(profileDTO);
    }


}