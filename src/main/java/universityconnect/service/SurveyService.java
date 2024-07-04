package universityconnect.service;

import universityconnect.dto.QuestionDTO;
import universityconnect.dto.SurveyDTO;

import java.util.List;

public interface SurveyService {

    SurveyDTO createSurvey(SurveyDTO survey);

    void deleteSurvey(Long id);

    SurveyDTO getSurveyById(Long id);

    List<SurveyDTO> getAllSurveys();

    QuestionDTO createQuestion(QuestionDTO question);


}
