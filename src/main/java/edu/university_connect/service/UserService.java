package edu.university_connect.service;

import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.model.contract.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserDto> getPage(Pageable pageable);
    List<UserDto> getAll();
    UserDto getById(Long id);

    UserDto create(UserCreateRequest createRequest);

    UserDto update(Long id, UserUpdateRequest updateRequest);

    boolean delete(Long id);


}