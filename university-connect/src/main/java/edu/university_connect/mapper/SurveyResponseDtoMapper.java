package edu.university_connect.mapper;
import edu.university_connect.domain.entity.survey.SurveyResponse;
import edu.university_connect.model.contract.dto.SurveyAnswerDto;
import edu.university_connect.model.contract.request.survey.SurveyAnswerCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SurveyResponseDtoMapper {
    SurveyResponseDtoMapper MAPPER =
            Mappers.getMapper(SurveyResponseDtoMapper.class);
    SurveyResponse dtoToEntity(SurveyAnswerCreateRequest request);
    SurveyResponse dtoToEntity(SurveyQuestionUpdateRequest request);
    SurveyAnswerDto entityToDto(SurveyResponse survey);
}
