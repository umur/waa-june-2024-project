package edu.university_connect.service.action;

import edu.university_connect.model.contract.request.action.ActionCreateRequest;
import edu.university_connect.model.contract.request.action.ActionUpdateRequest;
import edu.university_connect.model.contract.dto.ActionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActionService {
    Page<ActionDto> getPage(Pageable pageable);
    List<ActionDto> getAll();
    ActionDto getById(Long id);

    ActionDto create(ActionCreateRequest createRequest);

    ActionDto update(Long id, ActionUpdateRequest updateRequest);

    boolean delete(Long id);


    ActionDto getByCode(String code);
}
