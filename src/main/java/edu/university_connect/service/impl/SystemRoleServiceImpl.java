package edu.university_connect.service.impl;

import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.SystemRoleDtoMapper;
import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.contract.dto.SystemRoleDto;
import edu.university_connect.model.contract.request.role.SystemRoleCreateRequest;
import edu.university_connect.model.contract.request.role.SystemRoleUpdateRequest;
import edu.university_connect.model.entity.SystemRole;
import edu.university_connect.repository.SystemRoleRepository;
import edu.university_connect.service.SystemRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class SystemRoleServiceImpl implements SystemRoleService {

    private final SystemRoleRepository repository;

    @Override
    public Page<SystemRoleDto> getPage(Pageable pageable) {
        Page<SystemRole> page = repository.findAll(pageable);
        return page.map(SystemRoleDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<SystemRoleDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SystemRoleDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SystemRoleDto getById(Long id) {
        Optional<SystemRole> sysRoleOpt= getSystemRoleById(id);
        if(sysRoleOpt.isPresent()){
            return SystemRoleDtoMapper.MAPPER.entityToDto(sysRoleOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "role",id.toString());
        }
    }

    @Override
    public SystemRoleDto create(SystemRoleCreateRequest createRequest) {
        SystemRole role=SystemRoleDtoMapper.MAPPER.dtoToEntity(createRequest);
        SystemRole savedRole=repository.save(role);
        return SystemRoleDtoMapper.MAPPER.entityToDto(savedRole);
    }

    @Override
    public SystemRoleDto update(Long id, SystemRoleUpdateRequest updateRequest) {
        Optional<SystemRole> sysRoleOpt= getSystemRoleById(id);
        if(sysRoleOpt.isPresent()){
            SystemRole role=sysRoleOpt.get();
            role.setName(updateRequest.getName());
            role.setCode(updateRequest.getCode());
            role.setDescription(updateRequest.getDescription());
            role.setActions(updateRequest.getActions());
            SystemRole savedRole=repository.save(role);
            return SystemRoleDtoMapper.MAPPER.entityToDto(savedRole);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "role");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    private Optional<SystemRole> getSystemRoleById(Long id){
        return repository.findById(id);
    }
}
