package universityconnect.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityconnect.domain.Student;
import universityconnect.domain.SurveyStudent;
import universityconnect.dto.SurveyStudentDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.StudentMapper;
import universityconnect.mapper.SurveyStudentMapper;
import universityconnect.repository.StudentRepository;
import universityconnect.repository.SurveyStudentRepository;
import universityconnect.service.SurveyStudentService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyStudentServiceImpl implements SurveyStudentService {

    private final SurveyStudentRepository surveyStudentRepository;
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;
    private final SurveyStudentMapper surveyStudentMapper;

    @Override
    public List<SurveyStudent> getAllSurveyStudents() {
        return surveyStudentRepository.findAll();
    }

    @Override
    public SurveyStudent getSurveyStudentById(Long id) {
        return surveyStudentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SurveyStudent with id " + id + " does not exist"));
    }

    @Override
    public SurveyStudent createSurveyStudent(SurveyStudent surveyStudent) {
        return surveyStudentRepository.save(surveyStudent);
    }

    @Override
    public void deleteSurveyStudent(Long id) {
        if (!surveyStudentRepository.existsById(id)) {
            throw new ResourceNotFoundException("SurveyStudent with id " + id + " does not exist");
        }
        surveyStudentRepository.deleteById(id);
    }

    @Override
    public List<SurveyStudent> getSurveysByStudent(Long studentId) {
        return surveyStudentRepository.findByStudentId(studentId);
    }


    @Override
    public SurveyStudentDTO submitSurvey(Long studentId, SurveyStudentDTO surveyStudent) {

        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            surveyStudentRepository.save(surveyStudentMapper.surveyStudentDTOToSurveyStudent(surveyStudent));
            return surveyStudent;
        } else {
            throw new ResourceNotFoundException("Student with id " + studentId + " does not exist");
        }
    }

}
