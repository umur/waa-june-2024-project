package edu.university_connect.service.impl;

import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.SystemActionDtoMapper;
import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.contract.dto.SystemActionDto;
import edu.university_connect.model.contract.request.action.SystemActionCreateRequest;
import edu.university_connect.model.contract.request.action.SystemActionUpdateRequest;
import edu.university_connect.model.entity.SystemAction;
import edu.university_connect.repository.SystemActionRepository;
import edu.university_connect.service.SystemActionService;
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
public class SystemActionServiceImpl implements SystemActionService {

    private final SystemActionRepository repository;

    @Override
    public Page<SystemActionDto> getPage(Pageable pageable) {
        Page<SystemAction> actionPage = repository.findAll(pageable);
        return actionPage.map(SystemActionDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<SystemActionDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SystemActionDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public SystemActionDto getById(Long id) {
        Optional<SystemAction> sysActionOpt=getSystemActionById(id);
        if(sysActionOpt.isPresent()){
            return SystemActionDtoMapper.MAPPER.entityToDto(sysActionOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "sys-action","id = "+id.toString());
        }
    }

    @Override
    public SystemActionDto create(SystemActionCreateRequest createRequest) {
        Optional<SystemAction> actionWithCode=repository.findByCodeIgnoreCase(createRequest.getCode());
        if(actionWithCode.isPresent()){
            throw ServiceException.of(AppStatusCode.E40006,"sys-action","code="+createRequest.getCode());
        }
        SystemAction action=SystemActionDtoMapper.MAPPER.dtoToEntity(createRequest);
        SystemAction savedAction=repository.save(action);
        return SystemActionDtoMapper.MAPPER.entityToDto(savedAction);
    }

    @Override
    public SystemActionDto update(Long id, SystemActionUpdateRequest updateRequest) {
        Optional<SystemAction> sysActionOpt=getSystemActionById(id);
        if(sysActionOpt.isPresent()){
            SystemAction action=sysActionOpt.get();
            action.setName(updateRequest.getName());
            action.setCode(updateRequest.getCode());
            action.setDescription(updateRequest.getDescription());
            SystemAction savedAction=repository.save(action);
            return SystemActionDtoMapper.MAPPER.entityToDto(savedAction);
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
    public SystemActionDto getByCode(String code) {
        Optional<SystemAction> sysActionOpt=getSystemActionByCode(code);
        if(sysActionOpt.isPresent()){
            return SystemActionDtoMapper.MAPPER.entityToDto(sysActionOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "sys-action","code = "+code);
        }
    }
    private Optional<SystemAction> getSystemActionByCode(String code){
        return repository.findByCodeIgnoreCase(code);
    }

    private Optional<SystemAction> getSystemActionById(Long id){
        return repository.findById(id);
    }
}
