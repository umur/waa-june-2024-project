package edu.university_connect.service.resource;

import edu.university_connect.domain.entity.Resource;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.ResourceDtoMapper;
import edu.university_connect.model.contract.dto.ResourceDto;
import edu.university_connect.model.contract.request.resource.ResourceRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.ResourceRepository;
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
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository repository;


    @Override
    public List<ResourceDto> getAll() {
        return repository.findAll()
                .stream()
                .map(ResourceDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public ResourceDto getById(Long id) {
        Optional<Resource> actionOpt=repository.findById(id);
        if(actionOpt.isPresent()){
            return ResourceDtoMapper.MAPPER.entityToDto(actionOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "resource","id = "+id);
        }
    }

    @Override
    public ResourceDto create(ResourceRequest createRequest) {
        Resource resource= ResourceDtoMapper.MAPPER.dtoToEntity(createRequest);
        Resource savedAction=repository.save(resource);
        return ResourceDtoMapper.MAPPER.entityToDto(savedAction);
    }

    @Override
    public ResourceDto update(Long id, ResourceRequest updateRequest) {
        Optional<Resource> actionOpt= repository.findById(id);
        if(actionOpt.isPresent()){
            Resource resource=actionOpt.get();
            resource.setTitle(updateRequest.getDescription());
            resource.setDescription(updateRequest.getTitle());
            //TODO uploaded file handling
            Resource savedAction=repository.save(resource);
            return ResourceDtoMapper.MAPPER.entityToDto(savedAction);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "resource");
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
    public Page<ResourceDto> getPage(Pageable pageable) {
        Page<Resource> resourcePage = repository.findAll(pageable);
        return resourcePage.map(ResourceDtoMapper.MAPPER::entityToDto);
    }


}
