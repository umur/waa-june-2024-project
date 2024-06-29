package edu.university_connect.service;

import edu.university_connect.model.contract.request.role.SystemRoleCreateRequest;
import edu.university_connect.model.contract.request.role.SystemRoleUpdateRequest;
import edu.university_connect.model.contract.dto.SystemRoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SystemRoleService {
    Page<SystemRoleDto> getPage(Pageable pageable);
    List<SystemRoleDto> getAll();
    SystemRoleDto getById(Long id);

    SystemRoleDto create(SystemRoleCreateRequest createRequest);

    SystemRoleDto update(Long id, SystemRoleUpdateRequest updateRequest);

    boolean delete(Long id);


}