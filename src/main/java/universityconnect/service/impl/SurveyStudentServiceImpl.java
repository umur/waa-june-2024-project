package universityconnect.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.SurveyStudent;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.repository.StudentRepository;
import universityconnect.repository.SurveyStudentRepository;
import universityconnect.service.SurveyStudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyStudentServiceImpl implements SurveyStudentService {

    private final SurveyStudentRepository surveyStudentRepository;
    private final StudentRepository studentRepository;

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
    public SurveyStudent submitSurvey(Long studentId, SurveyStudent surveyStudent) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    surveyStudent.setStudent(student);
                    return surveyStudentRepository.save(surveyStudent);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " does not exist"));
    }

}
