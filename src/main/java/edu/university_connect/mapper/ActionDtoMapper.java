package edu.university_connect.mapper;

import edu.university_connect.model.contract.request.action.ActionCreateRequest;
import edu.university_connect.model.contract.request.action.ActionUpdateRequest;
import edu.university_connect.model.contract.dto.ActionDto;
import edu.university_connect.model.entity.Action;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActionDtoMapper {
    ActionDtoMapper MAPPER =
            Mappers.getMapper(ActionDtoMapper.class);

    Action dtoToEntity(ActionCreateRequest request);
    Action dtoToEntity(ActionUpdateRequest request);
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    ActionDto entityToDto(Action action);


}