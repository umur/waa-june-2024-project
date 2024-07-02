package edu.university_connect.controller;

import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.contract.dto.ActionDto;
import edu.university_connect.model.contract.request.action.ActionCreateRequest;
import edu.university_connect.model.contract.request.action.ActionUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.action.ActionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actions")
@CrossOrigin
@Slf4j
public class ActionController {
    private final ActionService service;

    private final MessagingService messagingService;

    public ActionController(ActionService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ActionDto>>> getAll() {
        List<ActionDto> response= service.getAll();
        ApiResponse<List<ActionDto>> apiResponse =  new ApiResponse<List<ActionDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"action"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<ActionDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<ActionDto> response= service.getPage(pageable);
        ApiResponse<Page<ActionDto>> apiResponse =  new ApiResponse<Page<ActionDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"action"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<ActionDto>> create(@Valid @RequestBody ActionCreateRequest createRequest) {
        ActionDto response= service.create(createRequest);
        ApiResponse<ActionDto> apiResponse =  new ApiResponse<ActionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"action"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ActionDto>> get(@PathVariable Long id) {
        ActionDto response= service.getById(id);
        ApiResponse<ActionDto> apiResponse =  new ApiResponse<ActionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"action"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ActionDto>> update(@Valid @RequestBody ActionUpdateRequest updateRequest,
                                                         @PathVariable Long id) {
        ActionDto response= service.update(id,updateRequest);
        ApiResponse<ActionDto> apiResponse =  new ApiResponse<ActionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"action"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"action"}));
        return ResponseEntity.ok(apiResponse);
    }

}
