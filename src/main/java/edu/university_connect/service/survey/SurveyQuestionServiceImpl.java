package edu.university_connect.service.survey;
import edu.university_connect.domain.entity.survey.Survey;
import edu.university_connect.domain.entity.survey.SurveyQuestion;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.SurveyQuestionDtoMapper;
import edu.university_connect.model.contract.dto.SurveyQuestionDto;
import edu.university_connect.model.contract.request.survey.SurveyQuestionCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.SurveyQuestionRepository;
import edu.university_connect.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@RequiredArgsConstructor
@Service
@Transactional
public class SurveyQuestionServiceImpl implements SurveyQuestionService {
    private final SurveyQuestionRepository repository;
    private  final  SurveyRepository surveyRepository;
    @Override
    public Page<SurveyQuestionDto> getPage(Pageable pageable) {
        Page<SurveyQuestion> actionPage = repository.findAll(pageable);
        return actionPage.map(SurveyQuestionDtoMapper .MAPPER::entityToDto);
    }

    @Override
    public List<SurveyQuestionDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SurveyQuestionDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SurveyQuestionDto getById(Long id) {
        Optional<SurveyQuestion> surveyOpt= getSurveyQuestionById(id);
        if(surveyOpt.isPresent()){
            return SurveyQuestionDtoMapper .MAPPER.entityToDto(surveyOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "survey","id = "+id.toString());
        }
    }

    @Override
    public SurveyQuestionDto create(SurveyQuestionCreateRequest createRequest) {
        SurveyQuestion surveyQuestion= SurveyQuestionDtoMapper .MAPPER.dtoToEntity(createRequest);
        Optional<Survey> survey = surveyRepository.findById(createRequest.getSurveyId());
        if(survey.isPresent()){
            surveyQuestion.setSurvey(survey.get());
            SurveyQuestion savedQuestionSurvey=repository.save(surveyQuestion);
            return SurveyQuestionDtoMapper.MAPPER.entityToDto(savedQuestionSurvey);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "surveyQuestion");
        }
    }

    @Override
    public SurveyQuestionDto update(Long id, SurveyQuestionUpdateRequest updateRequest) {
        Optional<SurveyQuestion> surveyQuestionOpt= getSurveyQuestionById(id);
        if(surveyQuestionOpt.isPresent()){
            SurveyQuestion surveyQuestion=surveyQuestionOpt.get();
            surveyQuestion.setQuestion(updateRequest.getQuestion());
            return SurveyQuestionDtoMapper .MAPPER.entityToDto(surveyQuestion);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "surveyQuestion");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    private Optional<SurveyQuestion> getSurveyQuestionById(Long id){
        return repository.findById(id);
    }
}
