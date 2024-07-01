package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Survey;
import universityconnect.dto.SurveyDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.SurveyMapper;
import universityconnect.repository.SurveyRepository;
import universityconnect.service.SurveyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final SurveyMapper surveyMapper;


    @Override
    public SurveyDTO createSurvey(Survey survey) {
        Survey s = surveyRepository.saveAndFlush(survey);
        return surveyMapper.surveyToSurveyDTO(s);

    }

    @Override
    public void deleteSurvey(Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Survey with id " + id + " does not exist");
        }

        surveyRepository.deleteById(id);
    }

    @Override
    public SurveyDTO getSurveyById(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey with id " + id + " does not exist"));
        return surveyMapper.surveyToSurveyDTO(survey);
    }

    @Override
    public List<SurveyDTO> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        return surveys.stream()
                .map(surveyMapper::surveyToSurveyDTO)
                .collect(Collectors.toList());
    }


}
