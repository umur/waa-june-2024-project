package edu.university_connect.service.survey;
import edu.university_connect.config.ContextUser;
import edu.university_connect.domain.entity.Resource;
import edu.university_connect.domain.entity.User;
import edu.university_connect.domain.entity.survey.Survey;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.ResourceDtoMapper;
import edu.university_connect.mapper.SurveyDtoMapper ;
import edu.university_connect.model.contract.dto.ResourceDto;
import edu.university_connect.model.contract.dto.SurveyDto;
import edu.university_connect.model.contract.request.survey.SurveyCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.SurveyRepository;
import edu.university_connect.repository.UserRepository;
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
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;
    private final UserRepository userRepository;
    private final ContextUser contextUser;

    @Override
    public Page<SurveyDto> getPage(Pageable pageable) {
        Page<Survey> actionPage = repository.findAll(pageable);
        return actionPage.map(SurveyDtoMapper .MAPPER::entityToDto);
    }

    @Override
    public List<SurveyDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SurveyDtoMapper .MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SurveyDto getById(Long id) {
        Optional<Survey> surveyOpt= getSurveyById(id);
        if(surveyOpt.isPresent()){
            return SurveyDtoMapper .MAPPER.entityToDto(surveyOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "survey","id = "+id.toString());
        }
    }

    @Override
    public SurveyDto create(SurveyCreateRequest createRequest) {
        Long userId =  contextUser.getLoginUser().getId();
        Survey survey= SurveyDtoMapper .MAPPER.dtoToEntity(createRequest);
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            survey.setCreator(user.get());
            Survey savedSurvey=repository.save(survey);
            return SurveyDtoMapper.MAPPER.entityToDto(savedSurvey);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "survey");
        }
    }

    @Override
    public SurveyDto update(Long id, SurveyUpdateRequest updateRequest) {
        Optional<Survey> surveyOpt= getSurveyById(id);
        if(surveyOpt.isPresent()){
            Survey survey=surveyOpt.get();
            survey.setTitle(updateRequest.getTitle());
            return SurveyDtoMapper .MAPPER.entityToDto(survey);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "survey");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    private Optional<Survey> getSurveyById(Long id){
        return repository.findById(id);
    }

    @Override
    public Page<SurveyDto> getUserSurveyPage(Long id, Pageable pageable) {
        Page<Survey> resourcePage = repository.getUserSurveyPage(id,pageable);
        return resourcePage.map(SurveyDtoMapper.MAPPER::entityToDto);
    }
}
