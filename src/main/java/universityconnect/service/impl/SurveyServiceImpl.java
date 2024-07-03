package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Question;
import universityconnect.domain.Survey;
import universityconnect.dto.QuestionDTO;
import universityconnect.dto.SurveyDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.QuestionMapper;
import universityconnect.mapper.SurveyMapper;
import universityconnect.repository.QuestionRepository;
import universityconnect.repository.SurveyRepository;
import universityconnect.service.SurveyService;
import universityconnect.service.SurveyStudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final QuestionRepository questionRepository;

    private final SurveyMapper surveyMapper;

    private final QuestionMapper questionMapper;


    @Override
    public SurveyDTO createSurvey(SurveyDTO survey) {
        Survey reqSurvey = surveyMapper.surveyDTOToSurvey(survey);
        Survey s = surveyRepository.saveAndFlush(reqSurvey);
        return surveyMapper.surveyToSurveyDTO(s);

    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO question) {
        Question reqQ = questionMapper.questionDTOToQuestion(question);
        Question repQ = questionRepository.save(reqQ);
        return questionMapper.questionToQuestionDTO(repQ);
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
