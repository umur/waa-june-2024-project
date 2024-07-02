package edu.university_connect.controller;
import edu.university_connect.model.contract.dto.SurveyDto;
import edu.university_connect.model.contract.request.survey.SurveyCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.survey.SurveyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/surveys")
@CrossOrigin
@Slf4j
public class SurveyController {
    private final SurveyService service;

    private final MessagingService messagingService;

    public SurveyController(SurveyService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SurveyDto>>> getAll() {
        List<SurveyDto> response= service.getAll();
        ApiResponse<List<SurveyDto>> apiResponse =  new ApiResponse<List<SurveyDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"survey"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<SurveyDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<SurveyDto> response= service.getPage(pageable);
        ApiResponse<Page<SurveyDto>> apiResponse =  new ApiResponse<Page<SurveyDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"survey"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<SurveyDto>> create(@Valid @RequestBody SurveyCreateRequest createRequest) {
        SurveyDto response= service.create(createRequest);
        ApiResponse<SurveyDto> apiResponse =  new ApiResponse<SurveyDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"survey"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyDto>> get(@PathVariable Long id) {
        SurveyDto response= service.getById(id);
        ApiResponse<SurveyDto> apiResponse =  new ApiResponse<SurveyDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"survey"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyDto>> update(@Valid @RequestBody SurveyUpdateRequest updateRequest,
                                                         @PathVariable Long id) {
        SurveyDto response= service.update(id,updateRequest);
        ApiResponse<SurveyDto> apiResponse =  new ApiResponse<SurveyDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"survey"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"survey"}));
        return ResponseEntity.ok(apiResponse);
    }

}
