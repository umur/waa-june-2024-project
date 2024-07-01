package universityconnect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityconnect.domain.*;
import universityconnect.dto.ReportDTO;
import universityconnect.dto.UserDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.ReportMapper;
import universityconnect.mapper.UserMapper;
import universityconnect.repository.*;
import universityconnect.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));
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
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());

        if (userDTO.getBlockIds() != null) {
            List<Block> blocks = blockRepository.findAllById(userDTO.getBlockIds());
            existingUser.setBlocks(blocks);
        }

        if (userDTO.getDiscussionIds() != null) {
            List<Discussion> discussions = discussionRepository.findAllById(userDTO.getDiscussionIds());
            existingUser.setDiscussions(discussions);
        }

        if (userDTO.getEventIds() != null) {
            List<Event> events = eventRepository.findAllById(userDTO.getEventIds());
            existingUser.setEvents(events);
        }

        if (userDTO.getResourceIds() != null) {
            List<Resource> resources = resourceRepository.findAllById(userDTO.getResourceIds());
            existingUser.setResources(resources);
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + id));
        userRepository.delete(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + userId));
    }

    @Override
    public List<UserDTO> getAllReportedUsersByReporterUserId(Long id) {
        List<User> reportedUsers = reportRepository.findByReportedUserByReporterUserId(id);
        return reportedUsers.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllReporterUsersByReportedUserId(Long id) {
        List<User> reporterUsers = reportRepository.findByReporterUserByReportedUserId(id);
        return reporterUsers.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
     }
}
