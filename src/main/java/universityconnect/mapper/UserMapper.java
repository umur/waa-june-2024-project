package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Admin;
import universityconnect.domain.Role;
import universityconnect.domain.Student;
import universityconnect.domain.User;
import universityconnect.dto.UserDTO;
import universityconnect.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = UserService.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "blockIds", expression = "java(mapIds(user.getBlocks()))")
    @Mapping(target = "discussionIds", expression = "java(mapIds(user.getDiscussions()))")
    @Mapping(target = "resourceIds", expression = "java(mapIds(user.getResources()))")
    @Mapping(target = "eventIds", expression = "java(mapIds(user.getEvents()))")
    UserDTO userToUserDTO(User user);

    @Mapping(target = "blocks", ignore = true)
    @Mapping(target = "discussions", ignore = true)
    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "events", ignore = true)
    User userDTOToUser(UserDTO userDTO);

    default List<Long> mapIds(List<?> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> {
                    if (entity instanceof universityconnect.domain.Block) {
                        return ((universityconnect.domain.Block) entity).getId();
                    } else if (entity instanceof universityconnect.domain.Report) {
                        return ((universityconnect.domain.Report) entity).getId();
                    } else if (entity instanceof universityconnect.domain.Discussion) {
                        return ((universityconnect.domain.Discussion) entity).getId();
                    } else if (entity instanceof universityconnect.domain.Event) {
                        return ((universityconnect.domain.Event) entity).getId();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
