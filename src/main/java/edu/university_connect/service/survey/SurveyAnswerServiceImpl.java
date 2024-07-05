package edu.university_connect.service.survey;
import edu.university_connect.domain.entity.survey.SurveyQuestion;
import edu.university_connect.domain.entity.survey.SurveyResponse;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.SurveyResponseDtoMapper;
import edu.university_connect.model.contract.dto.SurveyAnswerDto;
import edu.university_connect.model.contract.request.survey.SurveyAnswerCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyAnswerUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.SurveyQuestionRepository;
import edu.university_connect.repository.SurveyAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class SurveyAnswerServiceImpl implements SurveyAnswerService {
    private final SurveyQuestionRepository surveyQuestionRepository;
    private  final SurveyAnswerRepository repository;
    @Override
    public Page<SurveyAnswerDto> getPage(Pageable pageable) {
        Page<SurveyResponse> surveyResponsePage = repository.findAll(pageable);
        return surveyResponsePage.map(SurveyResponseDtoMapper .MAPPER::entityToDto);
    }

    @Override
    public List<SurveyAnswerDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SurveyResponseDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SurveyAnswerDto getById(Long id) {
        Optional<SurveyResponse> surveyOpt= getSurveyResponseById(id);
        if(surveyOpt.isPresent()){
            return SurveyResponseDtoMapper .MAPPER.entityToDto(surveyOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "survey","id = "+id.toString());
        }
    }

//    @Override
//    public SurveyResponseDto create(SurveyResponseCreateRequest createRequest) {
//        SurveyResponse surveyResponse= SurveyResponseDtoMapper .MAPPER.dtoToEntity(createRequest);
//        Optional<SurveyQuestion> surveyQuestion =surveyQuestionRepository.findById(createRequest.getSurveyQuestionId());
//        if(surveyQuestion.isPresent()){
//            surveyResponse.setSurveyQuestion(surveyQuestion.get());
//            SurveyResponse savedQuestionSurvey=repository.save(surveyResponse);
//            return SurveyResponseDtoMapper.MAPPER.entityToDto(savedQuestionSurvey);
//        }
//        else {
//            throw ServiceException.of(AppStatusCode.E40000, "SurveyResponse");
//        }
//    }

    public List<SurveyAnswerDto> create(List<SurveyAnswerCreateRequest> createRequests) {
        List<SurveyResponse> surveyResponses = new ArrayList<>();

        for (SurveyAnswerCreateRequest createRequest : createRequests) {
            SurveyResponse surveyResponse = SurveyResponseDtoMapper.MAPPER.dtoToEntity(createRequest);

            Optional<SurveyQuestion> surveyQuestion = surveyQuestionRepository.findById(createRequest.getSurveyQuestionId());

            if (surveyQuestion.isPresent()) {
                surveyResponse.setSurveyQuestion(surveyQuestion.get());
                surveyResponses.add(surveyResponse);
            } else {
                throw ServiceException.of(AppStatusCode.E40000, "SurveyResponse");
            }
        }
        // Save all survey responses in one batch operation
        List<SurveyResponse> savedSurveyResponses = repository.saveAll(surveyResponses);
        // Convert saved entities to DTOs
        return savedSurveyResponses.stream()
                .map(SurveyResponseDtoMapper.MAPPER::entityToDto)
                .collect(Collectors.toList());
    }





    @Override
    public SurveyAnswerDto update(Long id, SurveyAnswerUpdateRequest updateRequest) {
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
