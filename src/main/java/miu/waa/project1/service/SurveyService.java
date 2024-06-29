package miu.waa.project1.service;


import miu.waa.project1.model.Survey;

import java.util.List;

public interface SurveyService {
    public Survey getSurveyById(Long id);
    public void createSurvey(Survey survey);
    public Survey updateSurvey(Long id, Survey survey);
    public void deleteSurvey(Long id);
    public Survey getSurveysByUserId(Long userId);

    List<Survey> getAllSurveys();
}
