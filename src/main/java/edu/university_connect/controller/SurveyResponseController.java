package edu.university_connect.controller;
import edu.university_connect.model.contract.dto.SurveyResponseDto;
import edu.university_connect.model.contract.request.survey.SurveyQuestionCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionUpdateRequest;
import edu.university_connect.model.contract.request.survey.SurveyResponseCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyResponseUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.survey.SurveyQuestionService;
import edu.university_connect.service.survey.SurveyResponseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveyResponses")
@CrossOrigin
@Slf4j
public class SurveyResponseController {
    private final SurveyResponseService service;

    private final MessagingService messagingService;

    public SurveyResponseController(SurveyResponseService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SurveyResponseDto>>> getAll() {
        List<SurveyResponseDto> response= service.getAll();
        ApiResponse<List<SurveyResponseDto>> apiResponse =  new ApiResponse<List<SurveyResponseDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<SurveyResponseDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<SurveyResponseDto> response= service.getPage(pageable);
        ApiResponse<Page<SurveyResponseDto>> apiResponse =  new ApiResponse<Page<SurveyResponseDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<SurveyResponseDto>> create(@Valid @RequestBody SurveyResponseCreateRequest createRequest) {
        SurveyResponseDto response= service.create(createRequest);
        ApiResponse<SurveyResponseDto> apiResponse =  new ApiResponse<SurveyResponseDto>();
        apiResponse.setResponseData(response);
       apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyResponseDto>> get(@PathVariable Long id) {
        SurveyResponseDto response= service.getById(id);
        ApiResponse<SurveyResponseDto> apiResponse =  new ApiResponse<SurveyResponseDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyResponseDto>> update(@Valid @RequestBody SurveyResponseUpdateRequest updateRequest,
                                                         @PathVariable Long id) {
        SurveyResponseDto response= service.update(id,updateRequest);
        ApiResponse<SurveyResponseDto> apiResponse =  new ApiResponse<SurveyResponseDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);
    }

}
