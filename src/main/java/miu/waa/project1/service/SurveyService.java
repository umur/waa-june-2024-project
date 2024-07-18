package miu.waa.project1.service;


import miu.waa.project1.dto.SurveyDTO;
import miu.waa.project1.model.Survey;

import java.util.List;

public interface SurveyService {
    public Survey getSurveyById(Long id);
    public void createSurvey(SurveyDTO survey);
    public Survey updateSurvey(Long id, SurveyDTO survey);
    public void deleteSurvey(Long id);
    public Survey getSurveysByUserId(Long userId);

    List<Survey> getAllSurveys();
}
