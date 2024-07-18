package edu.university_connect.mapper;

import edu.university_connect.domain.entity.Resource;
import edu.university_connect.model.contract.dto.ResourceDto;
import edu.university_connect.model.contract.request.resource.ResourceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceDtoMapper {
    ResourceDtoMapper MAPPER =
            Mappers.getMapper(ResourceDtoMapper.class);

    ResourceDto entityToDto(Resource request);

    Resource dtoToEntity(ResourceRequest createRequest);
}
