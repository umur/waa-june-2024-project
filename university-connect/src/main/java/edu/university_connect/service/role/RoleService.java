package edu.university_connect.service.role;

import edu.university_connect.domain.entity.Role;
import edu.university_connect.model.contract.request.role.RoleCreateRequest;
import edu.university_connect.model.contract.request.role.RoleUpdateRequest;
import edu.university_connect.model.contract.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Page<RoleDto> getPage(Pageable pageable);
    List<RoleDto> getAll();
    RoleDto getById(Long id);

    RoleDto create(RoleCreateRequest createRequest);

    RoleDto update(Long id, RoleUpdateRequest updateRequest);

    boolean delete(Long id);


    Optional<Role> getRoleById(Long id);

    Optional<Role> getRoleByCode(String code);
}