package edu.university_connect.mapper;

import edu.university_connect.model.contract.request.action.SystemActionCreateRequest;
import edu.university_connect.model.contract.request.action.SystemActionUpdateRequest;
import edu.university_connect.model.contract.dto.SystemActionDto;
import edu.university_connect.model.entity.SystemAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemActionDtoMapper {
    SystemActionDtoMapper MAPPER =
            Mappers.getMapper(SystemActionDtoMapper.class);

    SystemAction dtoToEntity(SystemActionCreateRequest request);
    SystemAction dtoToEntity(SystemActionUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    SystemActionDto entityToDto(SystemAction action);


}