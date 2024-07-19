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

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
