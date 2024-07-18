package edu.university_connect.service.student;

import edu.university_connect.domain.entity.Student;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.StudentDtoMapper;
import edu.university_connect.model.contract.dto.StudentDto;
import edu.university_connect.model.contract.request.student.StudentCreateRequest;
import edu.university_connect.model.contract.request.student.StudentUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository repository;
    @Override
    public Optional<Student> getByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }

    @Override
    public Page<StudentDto> getPage(Pageable pageable) {
        Page<Student> studentPage = repository.findAll(pageable);
        return studentPage.map(StudentDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<StudentDto> getAll() {
        return repository.findAll()
                .stream()
                .map(StudentDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public StudentDto getById(Long id) {
        Optional<Student> studentOpt= getStudentById(id);
        if(studentOpt.isPresent()){
            return StudentDtoMapper.MAPPER.entityToDto(studentOpt.get());
        }
        else {
            log.error("Student with id {} does not exist",id);
            throw ServiceException.of(AppStatusCode.E40000, "Student","id = "+id.toString());
        }
    }

    @Override
    public StudentDto create(StudentCreateRequest createRequest) {
        Optional<Student> studentWithEmail=repository.findByEmailIgnoreCase(createRequest.getEmail());
        if(studentWithEmail.isPresent()){
            log.error("User with code {} already exist",createRequest.getEmail());
            throw ServiceException.of(AppStatusCode.E40006,"student","email="+createRequest.getEmail());
        }
        Student student= StudentDtoMapper.MAPPER.dtoToEntity(createRequest);
        Student savedStudent=saveStudent(student);
        return StudentDtoMapper.MAPPER.entityToDto(savedStudent);
    }

    @Override
    public StudentDto update(Long id, StudentUpdateRequest updateRequest) {
        Optional<Student> studentOpt= getStudentById(id);
        if(studentOpt.isPresent()){
            Student student=studentOpt.get();
            student.setEmail(updateRequest.getEmail());
            student.setMajor(updateRequest.getMajor());
            student.setYear(String.valueOf(updateRequest.getYear()));
            student.setFirstName(updateRequest.getFirstName());
            student.setLastName(updateRequest.getLastName());
            Student savedStudent=saveStudent(student);
            return StudentDtoMapper.MAPPER.entityToDto(savedStudent);
        }
        else {
            log.error("Student with id {} does not exist",id);
            throw ServiceException.of(AppStatusCode.E40000, "student");
        }
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        Optional<Student> studentOpt= repository.findByEmailIgnoreCase(email);
        if(studentOpt.isEmpty()){
            log.error("Student with email {} does not exist",email);
            return Optional.empty();
        }
        Student student=studentOpt.get();
        student.setUser(student.getUser());
        return Optional.of(student);
    }

    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }
}
