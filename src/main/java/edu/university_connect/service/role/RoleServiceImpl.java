package edu.university_connect.service.role;

import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.RoleDtoMapper;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.contract.dto.RoleDto;
import edu.university_connect.model.contract.request.role.RoleCreateRequest;
import edu.university_connect.model.contract.request.role.RoleUpdateRequest;
import edu.university_connect.domain.entity.Role;
import edu.university_connect.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Page<RoleDto> getPage(Pageable pageable) {
        Page<Role> page = repository.findAll(pageable);
        return page.map(RoleDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<RoleDto> getAll() {
        return repository.findAll()
                .stream()
                .map(RoleDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public RoleDto getById(Long id) {
        Optional<Role> roleOpt= getRoleById(id);
        if(roleOpt.isPresent()){
            return RoleDtoMapper.MAPPER.entityToDto(roleOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "role",id.toString());
        }
    }

    @Override
    public RoleDto create(RoleCreateRequest createRequest) {
        Optional<Role> roleWithCode=repository.findByCodeIgnoreCase(createRequest.getCode());
        if(roleWithCode.isPresent()){
            throw ServiceException.of(AppStatusCode.E40006,"role","code="+createRequest.getCode());
        }
        //check valid action codes
        Role role= RoleDtoMapper.MAPPER.dtoToEntity(createRequest);
        Role savedRole=repository.save(role);
        return RoleDtoMapper.MAPPER.entityToDto(savedRole);
    }

    @Override
    public RoleDto update(Long id, RoleUpdateRequest updateRequest) {
        Optional<Role> roleOpt= getRoleById(id);
        if(roleOpt.isPresent()){
            //check valid action codes
            Role role=roleOpt.get();
            role.setName(updateRequest.getName());
            role.setCode(updateRequest.getCode());
            role.setDescription(updateRequest.getDescription());
            role.setActions(updateRequest.getActions());
            Role savedRole=repository.save(role);
            return RoleDtoMapper.MAPPER.entityToDto(savedRole);
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

    @Override
    public Optional<Role> getRoleById(Long id){
        return repository.findById(id);
    }

    @Override
    public Optional<Role> getRoleByCode(String code){
        return repository.findByCodeIgnoreCase(code);
    }
}
