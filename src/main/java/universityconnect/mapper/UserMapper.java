package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.User;
import universityconnect.dto.UserDTO;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "blockIds", source = "blocks.id")
    @Mapping(target = "reportedListIds", source = "reportedLists.id")
    @Mapping(target = "discussionIds", source = "discussions.id")
    @Mapping(target = "resourceIds", source = "resources.id")
    @Mapping(target = "eventIds", source = "events.id")
    UserDTO userToUserDTO(User user);

    @Mapping(target = "blocks", ignore = true)
    @Mapping(target = "reportedLists", ignore = true)
    @Mapping(target = "discussions", ignore = true)
    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "events", ignore = true)
    User userDTOToUser(UserDTO userDTO);
}

