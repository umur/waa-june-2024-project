package edu.university_connect.mapper;

import edu.university_connect.model.contract.request.user.SystemUserCreateRequest;
import edu.university_connect.model.contract.request.user.SystemUserUpdateRequest;
import edu.university_connect.model.contract.dto.SystemUserDto;
import edu.university_connect.model.entity.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemUserDtoMapper {
    SystemUserDtoMapper MAPPER =
            Mappers.getMapper(SystemUserDtoMapper.class);

    SystemUser dtoToEntity(SystemUserCreateRequest request);
    SystemUser dtoToEntity(SystemUserUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    SystemUserDto entityToDto(SystemUser user);


}