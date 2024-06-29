package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Answer;
import universityconnect.domain.Question;
import universityconnect.dto.QuestionDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "answerIds", source = "answers")
    QuestionDTO questionToQuestionDTO(Question question);

    @Mapping(target = "answers", ignore = true)
    Question questionDTOToQuestion(QuestionDTO questionDTO);

    default List<Long> mapAnswerIds(List<Answer> answers) {
        return answers.stream()
                .map(Answer::getId)
                .collect(Collectors.toList());
    }
}

