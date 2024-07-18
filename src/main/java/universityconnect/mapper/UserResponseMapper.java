package universityconnect.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.User;
import universityconnect.dto.UserResponse;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    UserResponse userToUserResponse(User user);

    User userResponseToUser(UserResponse userResponse);
}
