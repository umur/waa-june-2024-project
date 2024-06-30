package edu.university_connect.mapper;

import edu.university_connect.domain.entity.User;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.domain.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    UserDtoMapper MAPPER =
            Mappers.getMapper(UserDtoMapper.class);

    User dtoToEntity(UserCreateRequest request);
    User dtoToEntity(UserUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    UserDto entityToDto(User user);


}