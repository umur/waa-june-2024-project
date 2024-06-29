package miu.waa.project1.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import miu.waa.project1.model.Survey;
import miu.waa.project1.repository.SurveyRepository;
import miu.waa.project1.service.SurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@Getter
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id).orElse(null);
    }

    @Override
    public void createSurvey(Survey survey) {
        surveyRepository.save(survey);
    }

    @Override
    public Survey updateSurvey(Long id, Survey s) {
        Survey survey = surveyRepository.findById(id).orElse(null);
        if (survey != null) {
            survey.setName(s.getName());
            survey.setDate(s.getDate());
            survey.setTitle(s.getTitle());
            survey.setComment(s.getComment());
            survey.setDescription(s.getDescription());
            survey.setEmail(s.getEmail());
            survey.setRate(s.getRate());
        }
        return surveyRepository.save(survey);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public Survey getSurveysByUserId(Long userId) {
        return surveyRepository.findAllByUserId(userId);
    }
}
