package universityconnect.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityconnect.domain.User;
import universityconnect.dto.UserDTO;
import universityconnect.mapper.UserMapper;
import universityconnect.repository.UserRepository;
import universityconnect.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return userMapper.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Update the existingUser fields based on userDTO
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        // Update other fields as needed

        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        userRepository.delete(user);
    }
}

