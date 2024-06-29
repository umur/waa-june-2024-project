package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Question;
import universityconnect.dto.QuestionDTO;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "answerIds", source = "answers.id")
    QuestionDTO questionToQuestionDTO(Question question);

    @Mapping(target = "answers", ignore = true)
    Question questionDTOToQuestion(QuestionDTO questionDTO);
}
