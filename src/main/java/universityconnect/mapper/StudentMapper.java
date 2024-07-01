package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Student;
import universityconnect.dto.StudentDTO;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO studentToStudentDTO(Student student);

    Student studentDTOToStudent(StudentDTO studentDTO);
}

