package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Profile;
import universityconnect.dto.ProfileDTO;

@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = "userId", source = "user.id")
    ProfileDTO profileToProfileDTO(Profile profile);

    @Mapping(target = "user", ignore = true)
    Profile profileDTOToProfile(ProfileDTO profileDTO);
}

