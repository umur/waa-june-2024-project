package universityconnect.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Student;
import universityconnect.dto.StudentResponse;

@Mapper(componentModel = "spring")
public interface StudentResponseMapper {
    StudentResponseMapper INSTANCE = Mappers.getMapper(StudentResponseMapper.class);

    StudentResponse studentToStudentResponse(Student student);

    Student studentResponseToStudent(StudentResponse studentResponse);
}
