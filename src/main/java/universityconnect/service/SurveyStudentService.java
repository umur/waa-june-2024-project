package universityconnect.service;

import universityconnect.domain.SurveyStudent;
import universityconnect.dto.SurveyStudentDTO;

import java.util.List;

public interface SurveyStudentService {

    List<SurveyStudent> getAllSurveyStudents();

    SurveyStudent getSurveyStudentById(Long id);

    SurveyStudent createSurveyStudent(SurveyStudent surveyStudent);

    void deleteSurveyStudent(Long id);

    List<SurveyStudent> getSurveysByStudent(Long studentId);

    SurveyStudentDTO submitSurvey(Long studentId, SurveyStudentDTO surveyStudent);
}
