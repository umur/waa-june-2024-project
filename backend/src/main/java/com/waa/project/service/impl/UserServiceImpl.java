package com.waa.project.service.impl;

import com.waa.project.entity.User;
import com.waa.project.exception.ResourceNotFoundException;
import com.waa.project.repository.UserRepository;
import com.waa.project.security.contract.AuthUserResponse;
import com.waa.project.service.UserService;
import com.waa.project.util.UserErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper    modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper    = modelMapper;
    }

    @Override
    public AuthUserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(
                                          () -> new ResourceNotFoundException(
                                                  UserErrorMessages.userNotFound(username)));

        return modelMapper.map(user, AuthUserResponse.class);
    }
}
