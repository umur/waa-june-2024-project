package edu.university_connect.service.impl;

import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.UserDtoMapper;
import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.model.entity.User;
import edu.university_connect.repository.UserRepository;
import edu.university_connect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    @Override
    public Page<UserDto> getPage(Pageable pageable) {
        Page<User> page = repository.findAll(pageable);
        return page.map(UserDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(UserDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> userOpt= getUserById(id);
        if(userOpt.isPresent()){
            return UserDtoMapper.MAPPER.entityToDto(userOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
    }

    @Override
    public UserDto create(UserCreateRequest createRequest) {

        User user= UserDtoMapper.MAPPER.dtoToEntity(createRequest);
        User savedUser=repository.save(user);
        return UserDtoMapper.MAPPER.entityToDto(savedUser);
    }

    @Override
    public UserDto update(Long id, UserUpdateRequest updateRequest) {
        Optional<User> userOpt= getUserById(id);
        if(userOpt.isPresent()){
            User user=userOpt.get();
            user.setUsername(updateRequest.getUsername());
            user.setEmail(updateRequest.getEmail());
            user.setEnabled(updateRequest.isEnabled());
            User savedUser=repository.save(user);
            return UserDtoMapper.MAPPER.entityToDto(savedUser);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    private Optional<User> getUserById(Long id){
        return repository.findById(id);
    }
}
