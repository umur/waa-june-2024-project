package edu.university_connect.service.survey;
import edu.university_connect.model.contract.dto.SurveyDto;
import edu.university_connect.model.contract.dto.SurveyQuestionDto;
import edu.university_connect.model.contract.request.survey.SurveyCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionUpdateRequest;
import edu.university_connect.model.contract.request.survey.SurveyUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SurveyQuestionService {
    Page<SurveyQuestionDto> getPage(Pageable pageable);
    List<SurveyQuestionDto> getAll();
    SurveyQuestionDto getById(Long id);
    SurveyQuestionDto create(SurveyQuestionCreateRequest createRequest);
    SurveyQuestionDto update(Long id, SurveyQuestionUpdateRequest updateRequest);
    boolean delete(Long id);
}
