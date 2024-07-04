package edu.university_connect.controller;

import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.contract.dto.RoleDto;
import edu.university_connect.model.contract.request.role.RoleCreateRequest;
import edu.university_connect.model.contract.request.role.RoleUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.role.RoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
@Slf4j
public class RoleController {
    private final RoleService service;

    private final MessagingService messagingService;

    public RoleController(RoleService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('view_role')")
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAll() {
        List<RoleDto> response= service.getAll();
        ApiResponse<List<RoleDto>> apiResponse =  new ApiResponse<List<RoleDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"role"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('view_role')")
    public ResponseEntity<ApiResponse<Page<RoleDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<RoleDto> response= service.getPage(pageable);
        ApiResponse<Page<RoleDto>> apiResponse =  new ApiResponse<Page<RoleDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"role"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('create_role')")
    public ResponseEntity<ApiResponse<RoleDto>> create(@Valid @RequestBody RoleCreateRequest createRequest) {
        RoleDto response= service.create(createRequest);
        ApiResponse<RoleDto> apiResponse =  new ApiResponse<RoleDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"role"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('view_role')")
    public ResponseEntity<ApiResponse<RoleDto>> get(@PathVariable Long id) {
        RoleDto response= service.getById(id);
        ApiResponse<RoleDto> apiResponse =  new ApiResponse<RoleDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"role"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('modify_role')")
    public ResponseEntity<ApiResponse<RoleDto>> update(@Valid @RequestBody RoleUpdateRequest updateRequest,
                                                       @PathVariable Long id) {
        RoleDto response= service.update(id,updateRequest);
        ApiResponse<RoleDto> apiResponse =  new ApiResponse<RoleDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"role"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete_role')")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"role"}));
        return ResponseEntity.ok(apiResponse);
    }

}
