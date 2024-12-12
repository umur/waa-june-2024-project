package edu.university_connect.mapper;

import edu.university_connect.domain.entity.Profile;
import edu.university_connect.model.contract.dto.ProfileDto;
import edu.university_connect.model.contract.request.profile.ProfileRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileDtoMapper {
    ProfileDtoMapper MAPPER =
            Mappers.getMapper(ProfileDtoMapper.class);

    ProfileDto entityToDto(Profile request);

    Profile dtoToEntity(ProfileRequest request);
}
