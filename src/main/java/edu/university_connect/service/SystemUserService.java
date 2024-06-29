package edu.university_connect.service;

import edu.university_connect.model.contract.request.user.SystemUserCreateRequest;
import edu.university_connect.model.contract.request.user.SystemUserUpdateRequest;
import edu.university_connect.model.contract.dto.SystemUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SystemUserService {
    Page<SystemUserDto> getPage(Pageable pageable);
    List<SystemUserDto> getAll();
    SystemUserDto getById(Long id);

    SystemUserDto create(SystemUserCreateRequest createRequest);

    SystemUserDto update(Long id, SystemUserUpdateRequest updateRequest);

    boolean delete(Long id);


}