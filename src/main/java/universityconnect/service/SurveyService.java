package universityconnect.service;

import universityconnect.dto.AnswerDTO;
import universityconnect.dto.QuestionDTO;
import universityconnect.dto.SurveyDTO;
import universityconnect.dto.SurveyStudentDTO;

import java.util.List;

public interface SurveyService {

    SurveyDTO createSurvey(SurveyDTO survey);

    void deleteSurvey(Long id);

    SurveyDTO getSurveyById(Long id);

    List<SurveyDTO> getAllSurveys();

    QuestionDTO createQuestion(Long surveyId, QuestionDTO question);

    List<QuestionDTO> getQuestionsBySurvey(Long surveyId);

    void createAnswer(Long surveyId,Long questionId,Long studentId, AnswerDTO answer);

    List<SurveyStudentDTO> getSurveysByStudent(Long studentId);

    void deleteQuestion(Long surveyId, Long questionId);

    void updateQuestion(Long surveyId, Long questionId, QuestionDTO question);

}
