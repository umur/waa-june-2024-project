package edu.university_connect.mapper;

import edu.university_connect.model.contract.request.role.SystemRoleCreateRequest;
import edu.university_connect.model.contract.request.role.SystemRoleUpdateRequest;
import edu.university_connect.model.contract.dto.SystemRoleDto;
import edu.university_connect.model.entity.SystemRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemRoleDtoMapper {
    SystemRoleDtoMapper MAPPER =
            Mappers.getMapper(SystemRoleDtoMapper.class);

    SystemRole dtoToEntity(SystemRoleCreateRequest request);
    SystemRole dtoToEntity(SystemRoleUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    SystemRoleDto entityToDto(SystemRole role);


}