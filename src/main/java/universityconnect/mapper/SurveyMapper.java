package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Survey;
import universityconnect.dto.SurveyDTO;

@Mapper
public interface SurveyMapper {
    SurveyMapper INSTANCE = Mappers.getMapper(SurveyMapper.class);

    @Mapping(target = "questionIds", source = "questions.id")
    SurveyDTO surveyToSurveyDTO(Survey survey);

    @Mapping(target = "questions", ignore = true)
    Survey surveyDTOToSurvey(SurveyDTO surveyDTO);
}
