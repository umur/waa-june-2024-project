package universityconnect.service;

import universityconnect.domain.SurveyStudent;

import java.util.List;

public interface SurveyStudentService {

    List<SurveyStudent> getAllSurveyStudents();

    SurveyStudent getSurveyStudentById(Long id);

    SurveyStudent createSurveyStudent(SurveyStudent surveyStudent);

    void deleteSurveyStudent(Long id);

    List<SurveyStudent> getSurveysByStudent(Long studentId);

    SurveyStudent submitSurvey(Long studentId, SurveyStudent surveyStudent);
}
