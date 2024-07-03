package edu.university_connect.controller;
import edu.university_connect.model.contract.dto.SurveyQuestionDto;
import edu.university_connect.model.contract.request.survey.SurveyQuestionCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyQuestionUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.survey.SurveyQuestionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveyQuestions")
@CrossOrigin
@Slf4j
public class SurveyQuestionController {
    private final SurveyQuestionService service;

    private final MessagingService messagingService;

    public SurveyQuestionController(SurveyQuestionService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SurveyQuestionDto>>> getAll() {
        List<SurveyQuestionDto> response= service.getAll();
        ApiResponse<List<SurveyQuestionDto>> apiResponse =  new ApiResponse<List<SurveyQuestionDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"surveyQuestion"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<SurveyQuestionDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<SurveyQuestionDto> response= service.getPage(pageable);
        ApiResponse<Page<SurveyQuestionDto>> apiResponse =  new ApiResponse<Page<SurveyQuestionDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"surveyQuestion"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<SurveyQuestionDto>> create(@Valid @RequestBody SurveyQuestionCreateRequest createRequest) {
        SurveyQuestionDto response= service.create(createRequest);
        ApiResponse<SurveyQuestionDto> apiResponse =  new ApiResponse<SurveyQuestionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"surveyQuestion"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyQuestionDto>> get(@PathVariable Long id) {
        SurveyQuestionDto response= service.getById(id);
        ApiResponse<SurveyQuestionDto> apiResponse =  new ApiResponse<SurveyQuestionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"surveyQuestion"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyQuestionDto>> update(@Valid @RequestBody SurveyQuestionUpdateRequest updateRequest,
                                                         @PathVariable Long id) {
        SurveyQuestionDto response= service.update(id,updateRequest);
        ApiResponse<SurveyQuestionDto> apiResponse =  new ApiResponse<SurveyQuestionDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"surveyQuestion"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"surveyQuestion"}));
        return ResponseEntity.ok(apiResponse);
    }

}
