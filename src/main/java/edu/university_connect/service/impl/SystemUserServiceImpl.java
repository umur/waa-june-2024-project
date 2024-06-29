package edu.university_connect.service.impl;

import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.SystemUserDtoMapper;
import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.contract.dto.SystemUserDto;
import edu.university_connect.model.contract.request.user.SystemUserCreateRequest;
import edu.university_connect.model.contract.request.user.SystemUserUpdateRequest;
import edu.university_connect.model.entity.SystemUser;
import edu.university_connect.repository.SystemUserRepository;
import edu.university_connect.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository repository;


    @Override
    public Page<SystemUserDto> getPage(Pageable pageable) {
        Page<SystemUser> page = repository.findAll(pageable);
        return page.map(SystemUserDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<SystemUserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SystemUserDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SystemUserDto getById(Long id) {
        Optional<SystemUser> sysUserOpt= getSystemUserById(id);
        if(sysUserOpt.isPresent()){
            return SystemUserDtoMapper.MAPPER.entityToDto(sysUserOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
    }

    @Override
    public SystemUserDto create(SystemUserCreateRequest createRequest) {
        SystemUser user=SystemUserDtoMapper.MAPPER.dtoToEntity(createRequest);
        SystemUser savedUser=repository.save(user);
        return SystemUserDtoMapper.MAPPER.entityToDto(savedUser);
    }

    @Override
    public SystemUserDto update(Long id, SystemUserUpdateRequest updateRequest) {
        Optional<SystemUser> sysUserOpt= getSystemUserById(id);
        if(sysUserOpt.isPresent()){
            SystemUser user=sysUserOpt.get();
            user.setUsername(updateRequest.getUsername());
            user.setEmail(updateRequest.getEmail());
            user.setEnabled(updateRequest.isEnabled());
            SystemUser savedUser=repository.save(user);
            return SystemUserDtoMapper.MAPPER.entityToDto(savedUser);
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

    private Optional<SystemUser> getSystemUserById(Long id){
        return repository.findById(id);
    }
}
