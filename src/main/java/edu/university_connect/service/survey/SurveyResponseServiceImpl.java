package edu.university_connect.service.survey;
import edu.university_connect.domain.entity.survey.SurveyQuestion;
import edu.university_connect.domain.entity.survey.SurveyResponse;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.SurveyResponseDtoMapper;
import edu.university_connect.model.contract.dto.SurveyResponseDto;
import edu.university_connect.model.contract.request.survey.SurveyResponseCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyResponseUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.SurveyQuestionRepository;
import edu.university_connect.repository.SurveyResponseRepository;
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
public class SurveyResponseServiceImpl implements SurveyResponseService {
    private final SurveyQuestionRepository surveyQuestionRepository;
    private  final SurveyResponseRepository repository;
    @Override
    public Page<SurveyResponseDto> getPage(Pageable pageable) {
        Page<SurveyResponse> surveyResponsePage = repository.findAll(pageable);
        return surveyResponsePage.map(SurveyResponseDtoMapper .MAPPER::entityToDto);
    }

    @Override
    public List<SurveyResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SurveyResponseDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SurveyResponseDto getById(Long id) {
        Optional<SurveyResponse> surveyOpt= getSurveyResponseById(id);
        if(surveyOpt.isPresent()){
            return SurveyResponseDtoMapper .MAPPER.entityToDto(surveyOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "survey","id = "+id.toString());
        }
    }

    @Override
    public SurveyResponseDto create(SurveyResponseCreateRequest createRequest) {
        SurveyResponse surveyResponse= SurveyResponseDtoMapper .MAPPER.dtoToEntity(createRequest);
        Optional<SurveyQuestion> surveyQuestion =surveyQuestionRepository.findById(createRequest.getSurveyQuestionId());
        if(surveyQuestion.isPresent()){
            surveyResponse.setSurveyQuestion(surveyQuestion.get());
            SurveyResponse savedQuestionSurvey=repository.save(surveyResponse);
            return SurveyResponseDtoMapper.MAPPER.entityToDto(savedQuestionSurvey);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "SurveyResponse");
        }
    }

    @Override
    public SurveyResponseDto update(Long id, SurveyResponseUpdateRequest updateRequest) {
        Optional<SurveyResponse> surveyResponseOpt= getSurveyResponseById(id);
        if(surveyResponseOpt.isPresent()){
            SurveyResponse surveyResponse=surveyResponseOpt.get();
            surveyResponse.setResponse(updateRequest.getResponse());
            return SurveyResponseDtoMapper .MAPPER.entityToDto(surveyResponse);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "SurveyResponse");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    private Optional<SurveyResponse> getSurveyResponseById(Long id){
        return repository.findById(id);
    }
}
