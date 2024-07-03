package edu.university_connect.service.survey;
import edu.university_connect.model.contract.dto.SurveyResponseDto;
import edu.university_connect.model.contract.request.survey.SurveyQuestionCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionUpdateRequest;
import edu.university_connect.model.contract.request.survey.SurveyResponseCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyResponseUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SurveyResponseService {
    Page<SurveyResponseDto> getPage(Pageable pageable);
    List<SurveyResponseDto> getAll();
    SurveyResponseDto getById(Long id);
    SurveyResponseDto create(SurveyResponseCreateRequest createRequest);
    SurveyResponseDto update(Long id, SurveyResponseUpdateRequest updateRequest);
    boolean delete(Long id);
}
