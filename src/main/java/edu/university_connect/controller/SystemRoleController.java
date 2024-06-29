package edu.university_connect.controller;

import edu.university_connect.exception.ServiceException;
import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.contract.dto.SystemRoleDto;
import edu.university_connect.model.contract.request.role.SystemRoleCreateRequest;
import edu.university_connect.model.contract.request.role.SystemRoleUpdateRequest;
import edu.university_connect.model.contract.response.common.ApiResponse;
import edu.university_connect.model.entity.SystemAction;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.SystemRoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/system-role")
@CrossOrigin
@Slf4j
public class SystemRoleController {
    private final SystemRoleService service;

    private final MessagingService messagingService;

    public SystemRoleController(SystemRoleService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SystemRoleDto>>> getAll() {
        List<SystemRoleDto> response= service.getAll();
        ApiResponse<List<SystemRoleDto>> apiResponse =  new ApiResponse<List<SystemRoleDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"sys-role"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<SystemRoleDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<SystemRoleDto> response= service.getPage(pageable);
        ApiResponse<Page<SystemRoleDto>> apiResponse =  new ApiResponse<Page<SystemRoleDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"sys-role"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<SystemRoleDto>> create(@Valid @RequestBody SystemRoleCreateRequest createRequest) {
        SystemRoleDto response= service.create(createRequest);
        ApiResponse<SystemRoleDto> apiResponse =  new ApiResponse<SystemRoleDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"sys-role"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemRoleDto>> get(@PathVariable Long id) {
        SystemRoleDto response= service.getById(id);
        ApiResponse<SystemRoleDto> apiResponse =  new ApiResponse<SystemRoleDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"sys-role"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemRoleDto>> update(@Valid @RequestBody SystemRoleUpdateRequest updateRequest,
                                                              @PathVariable Long id) {
        SystemRoleDto response= service.update(id,updateRequest);
        ApiResponse<SystemRoleDto> apiResponse =  new ApiResponse<SystemRoleDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"sys-role"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"sys-role"}));
        return ResponseEntity.ok(apiResponse);
    }

}
