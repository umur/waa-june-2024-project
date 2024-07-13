package edu.university_connect.mapper;
import edu.university_connect.domain.entity.survey.Survey;
import edu.university_connect.domain.entity.survey.SurveyQuestion;
import edu.university_connect.model.contract.dto.SurveyDto;
import edu.university_connect.model.contract.dto.SurveyQuestionDto;
import edu.university_connect.model.contract.request.survey.SurveyCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionUpdateRequest;
import edu.university_connect.model.contract.request.survey.SurveyUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SurveyQuestionDtoMapper {
    SurveyQuestionDtoMapper MAPPER =
            Mappers.getMapper(SurveyQuestionDtoMapper.class);
    SurveyQuestion dtoToEntity(SurveyQuestionCreateRequest request);
    SurveyQuestion dtoToEntity(SurveyQuestionUpdateRequest request);
    SurveyQuestionDto entityToDto(SurveyQuestion survey);
}
