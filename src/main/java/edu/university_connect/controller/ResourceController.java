package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.ResourceDto;
import edu.university_connect.model.contract.request.resource.ResourceRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.resource.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService service;

    private final MessagingService messagingService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ResourceDto>>> getAll() {
        List<ResourceDto> response= service.getAll();
        ApiResponse<List<ResourceDto>> apiResponse =  new ApiResponse<List<ResourceDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"resource"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<ResourceDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<ResourceDto> response= service.getPage(pageable);
        ApiResponse<Page<ResourceDto>> apiResponse =  new ApiResponse<Page<ResourceDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"resource"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<ResourceDto>> create(@Valid @RequestBody ResourceRequest createRequest) {
        ResourceDto response= service.create(createRequest);
        ApiResponse<ResourceDto> apiResponse =  new ApiResponse<ResourceDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"resource"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceDto>> get(@PathVariable Long id) {
        ResourceDto response= service.getById(id);
        ApiResponse<ResourceDto> apiResponse =  new ApiResponse<ResourceDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"resource"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceDto>> update(@Valid @RequestBody ResourceRequest updateRequest,
                                                         @PathVariable Long id) {
        ResourceDto response= service.update(id,updateRequest);
        ApiResponse<ResourceDto> apiResponse =  new ApiResponse<ResourceDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"resource"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"resource"}));
        return ResponseEntity.ok(apiResponse);
    }

}
