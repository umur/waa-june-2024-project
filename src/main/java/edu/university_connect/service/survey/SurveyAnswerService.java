package edu.university_connect.service.survey;
import edu.university_connect.model.contract.dto.SurveyAnswerDto;
import edu.university_connect.model.contract.request.survey.SurveyAnswerCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyAnswerUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SurveyAnswerService {
    Page<SurveyAnswerDto> getPage(Pageable pageable);
    List<SurveyAnswerDto> getAll();
    SurveyAnswerDto getById(Long id);
    List<SurveyAnswerDto> create(List<SurveyAnswerCreateRequest> createRequests);
    SurveyAnswerDto update(Long id, SurveyAnswerUpdateRequest updateRequest);
    boolean delete(Long id);
}
