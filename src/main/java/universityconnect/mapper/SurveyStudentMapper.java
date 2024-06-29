package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.SurveyStudent;
import universityconnect.dto.SurveyStudentDTO;

@Mapper
public interface SurveyStudentMapper {
    SurveyStudentMapper INSTANCE = Mappers.getMapper(SurveyStudentMapper.class);

    @Mapping(target = "surveyId", source = "survey.id")
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "answerIds", source = "answers.id")
    SurveyStudentDTO surveyStudentToSurveyStudentDTO(SurveyStudent surveyStudent);

    @Mapping(target = "survey", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "answers", ignore = true)
    SurveyStudent surveyStudentDTOToSurveyStudent(SurveyStudentDTO surveyStudentDTO);
}
