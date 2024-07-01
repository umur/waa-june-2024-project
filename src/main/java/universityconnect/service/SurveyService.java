package universityconnect.service;

import universityconnect.domain.Survey;
import universityconnect.domain.SurveyStudent;
import universityconnect.dto.SurveyDTO;

import java.util.List;

public interface SurveyService {

    SurveyDTO createSurvey(Survey survey);

    void deleteSurvey(Long id);

    SurveyDTO getSurveyById(Long id);

    List<SurveyDTO> getAllSurveys();


}
