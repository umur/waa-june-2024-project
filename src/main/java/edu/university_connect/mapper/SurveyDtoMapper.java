package edu.university_connect.mapper;
import edu.university_connect.domain.entity.Action;
import edu.university_connect.domain.entity.survey.Survey;
import edu.university_connect.model.contract.dto.ActionDto;
import edu.university_connect.model.contract.dto.SurveyDto;
import edu.university_connect.model.contract.request.survey.SurveyCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SurveyDtoMapper {
    SurveyDtoMapper MAPPER =
            Mappers.getMapper(SurveyDtoMapper.class);
    Survey dtoToEntity(SurveyCreateRequest request);
    Survey dtoToEntity(SurveyUpdateRequest request);
    SurveyDto entityToDto(Survey survey);
}
