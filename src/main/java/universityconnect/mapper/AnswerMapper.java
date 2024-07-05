package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Answer;
import universityconnect.dto.AnswerDTO;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    AnswerDTO answerToAnswerDTO(Answer answer);

    Answer answerDTOToAnswer(AnswerDTO answerDTO);
}
