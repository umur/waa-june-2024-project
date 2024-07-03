package edu.university_connect.service.survey;
import edu.university_connect.model.contract.dto.SurveyDto;
import edu.university_connect.model.contract.request.survey.SurveyCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SurveyService {
    Page<SurveyDto> getPage(Pageable pageable);
    List<SurveyDto> getAll();
    SurveyDto getById(Long id);

    SurveyDto create(SurveyCreateRequest createRequest);

    SurveyDto update(Long id, SurveyUpdateRequest updateRequest);

    boolean delete(Long id);

}
