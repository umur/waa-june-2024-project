package edu.university_connect.mapper;

import edu.university_connect.model.contract.request.role.RoleCreateRequest;
import edu.university_connect.model.contract.request.role.RoleUpdateRequest;
import edu.university_connect.model.contract.dto.RoleDto;
import edu.university_connect.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {
    RoleDtoMapper MAPPER =
            Mappers.getMapper(RoleDtoMapper.class);

    Role dtoToEntity(RoleCreateRequest request);
    Role dtoToEntity(RoleUpdateRequest request);
    RoleDto entityToDto(Role role);


}