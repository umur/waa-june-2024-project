package edu.university_connect.service;

import edu.university_connect.model.contract.request.action.SystemActionCreateRequest;
import edu.university_connect.model.contract.request.action.SystemActionUpdateRequest;
import edu.university_connect.model.contract.dto.SystemActionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SystemActionService {
    Page<SystemActionDto> getPage(Pageable pageable);
    List<SystemActionDto> getAll();
    SystemActionDto getById(Long id);

    SystemActionDto create(SystemActionCreateRequest createRequest);

    SystemActionDto update(Long id, SystemActionUpdateRequest updateRequest);

    boolean delete(Long id);


    SystemActionDto getByCode(String code);
}
