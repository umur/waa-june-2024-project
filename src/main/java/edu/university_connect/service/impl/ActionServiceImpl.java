package edu.university_connect.service.impl;

import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.ActionDtoMapper;
import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.contract.dto.ActionDto;
import edu.university_connect.model.contract.request.action.ActionCreateRequest;
import edu.university_connect.model.contract.request.action.ActionUpdateRequest;
import edu.university_connect.model.entity.Action;
import edu.university_connect.repository.ActionRepository;
import edu.university_connect.service.ActionService;
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
public class ActionServiceImpl implements ActionService {

    private final ActionRepository repository;

    @Override
    public Page<ActionDto> getPage(Pageable pageable) {
        Page<Action> actionPage = repository.findAll(pageable);
        return actionPage.map(ActionDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<ActionDto> getAll() {
        return repository.findAll()
                .stream()
                .map(ActionDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public ActionDto getById(Long id) {
        Optional<Action> actionOpt= getActionById(id);
        if(actionOpt.isPresent()){
            return ActionDtoMapper.MAPPER.entityToDto(actionOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "action","id = "+id.toString());
        }
    }

    @Override
    public ActionDto create(ActionCreateRequest createRequest) {
        Optional<Action> actionWithCode=repository.findByCodeIgnoreCase(createRequest.getCode());
        if(actionWithCode.isPresent()){
            throw ServiceException.of(AppStatusCode.E40006,"action","code="+createRequest.getCode());
        }
        Action action= ActionDtoMapper.MAPPER.dtoToEntity(createRequest);
        Action savedAction=repository.save(action);
        return ActionDtoMapper.MAPPER.entityToDto(savedAction);
    }

    @Override
    public ActionDto update(Long id, ActionUpdateRequest updateRequest) {
        Optional<Action> actionOpt= getActionById(id);
        if(actionOpt.isPresent()){
            Action action=actionOpt.get();
            action.setName(updateRequest.getName());
            action.setCode(updateRequest.getCode());
            action.setDescription(updateRequest.getDescription());
            Action savedAction=repository.save(action);
            return ActionDtoMapper.MAPPER.entityToDto(savedAction);
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
    public ActionDto getByCode(String code) {
        Optional<Action> actionOpt= getActionByCode(code);
        if(actionOpt.isPresent()){
            return ActionDtoMapper.MAPPER.entityToDto(actionOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "action","code = "+code);
        }
    }
    private Optional<Action> getActionByCode(String code){
        return repository.findByCodeIgnoreCase(code);
    }

    private Optional<Action> getActionById(Long id){
        return repository.findById(id);
    }
}
