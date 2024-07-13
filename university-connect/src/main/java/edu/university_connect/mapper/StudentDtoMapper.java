package edu.university_connect.mapper;

import edu.university_connect.domain.entity.Student;
import edu.university_connect.domain.entity.User;
import edu.university_connect.model.contract.dto.StudentDto;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.auth.SignUpRequest;
import edu.university_connect.model.contract.request.student.StudentCreateRequest;
import edu.university_connect.model.contract.request.student.StudentUpdateRequest;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentDtoMapper {
    StudentDtoMapper MAPPER =
            Mappers.getMapper(StudentDtoMapper.class);

    @Mappings({
            @Mapping(target = "year", expression = "java(String.valueOf(request.getYear()))")
    })
    Student dtoToEntity(StudentCreateRequest request);
    Student dtoToEntity(StudentUpdateRequest request);
    StudentDto entityToDto(Student request);


}