package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.StudentDto;
import edu.university_connect.model.contract.request.student.StudentCreateRequest;
import edu.university_connect.model.contract.request.student.StudentUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.student.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentService service;

    private final MessagingService messagingService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<StudentDto>>> getAll() {
        List<StudentDto> response= service.getAll();
        ApiResponse<List<StudentDto>> apiResponse =  new ApiResponse<List<StudentDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"student"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<StudentDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<StudentDto> response= service.getPage(pageable);
        ApiResponse<Page<StudentDto>> apiResponse =  new ApiResponse<Page<StudentDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"student"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<StudentDto>> create(@Valid @RequestBody StudentCreateRequest createRequest) {
        StudentDto response= service.create(createRequest);
        ApiResponse<StudentDto> apiResponse =  new ApiResponse<StudentDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"student"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> get(@PathVariable Long id) {
        StudentDto response= service.getById(id);
        ApiResponse<StudentDto> apiResponse =  new ApiResponse<StudentDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"student"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> update(@Valid @RequestBody StudentUpdateRequest updateRequest,
                                                         @PathVariable Long id) {
        StudentDto response= service.update(id,updateRequest);
        ApiResponse<StudentDto> apiResponse =  new ApiResponse<StudentDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"student"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"student"}));
        return ResponseEntity.ok(apiResponse);
    }

}
