package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Question;
import universityconnect.domain.Survey;
import universityconnect.dto.SurveyDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface SurveyMapper {

    SurveyMapper INSTANCE = Mappers.getMapper(SurveyMapper.class);

    @Mapping(target = "questionIds", source = "questions")
    SurveyDTO surveyToSurveyDTO(Survey survey);

    @Mapping(target = "questions", ignore = true)
    Survey surveyDTOToSurvey(SurveyDTO surveyDTO);

    default List<Long> mapQuestionIds(List<Question> questions) {
        return questions.stream()
                .map(Question::getId)
                .collect(Collectors.toList());
    }
}
