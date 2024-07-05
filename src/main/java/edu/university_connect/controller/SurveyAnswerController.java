package edu.university_connect.controller;
import edu.university_connect.model.contract.dto.SurveyAnswerDto;
import edu.university_connect.model.contract.request.survey.SurveyAnswerCreateRequest;
import edu.university_connect.model.contract.request.survey.SurveyAnswerUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.survey.SurveyAnswerService;
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
@RequestMapping("/survey-answers")
@CrossOrigin
@Slf4j
public class SurveyAnswerController {
    private final SurveyAnswerService service;

    private final MessagingService messagingService;

    public SurveyAnswerController(SurveyAnswerService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('view_survey_response_list')")
    public ResponseEntity<ApiResponse<List<SurveyAnswerDto>>> getAll() {
        List<SurveyAnswerDto> response= service.getAll();
        ApiResponse<List<SurveyAnswerDto>> apiResponse =  new ApiResponse<List<SurveyAnswerDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/survey-question/{surveyQuestionId}")
    @PreAuthorize("hasAuthority('view_survey_response_list')")
    public ResponseEntity<ApiResponse<List<SurveyAnswerDto>>> getSurveyAnswersBySurveyQuestionId(@PathVariable Long surveyQuestionId) {
        List<SurveyAnswerDto> response= service.getSurveyAnswersBySurveyQuestionId(surveyQuestionId);
        ApiResponse<List<SurveyAnswerDto>> apiResponse =  new ApiResponse<List<SurveyAnswerDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("")
    @PreAuthorize("hasAuthority('view_survey_response_list')")
    public ResponseEntity<ApiResponse<Page<SurveyAnswerDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<SurveyAnswerDto> response= service.getPage(pageable);
        ApiResponse<Page<SurveyAnswerDto>> apiResponse =  new ApiResponse<Page<SurveyAnswerDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);

    }


    @PostMapping("")
    @PreAuthorize("hasAuthority('create_survey_response')")
    public ResponseEntity<ApiResponse<List<SurveyAnswerDto>>> create(@Valid @RequestBody List<SurveyAnswerCreateRequest> createRequests) {
        List<SurveyAnswerDto> responses = service.create(createRequests);
        ApiResponse<List<SurveyAnswerDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResponseData(responses);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002, new String[]{"surveyResponses"}));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('view_survey_response')")
    public ResponseEntity<ApiResponse<SurveyAnswerDto>> get(@PathVariable Long id) {
        SurveyAnswerDto response= service.getById(id);
        ApiResponse<SurveyAnswerDto> apiResponse =  new ApiResponse<SurveyAnswerDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('modify_survey_response')")
    public ResponseEntity<ApiResponse<SurveyAnswerDto>> update(@Valid @RequestBody SurveyAnswerUpdateRequest updateRequest,
                                                               @PathVariable Long id) {
        SurveyAnswerDto response= service.update(id,updateRequest);
        ApiResponse<SurveyAnswerDto> apiResponse =  new ApiResponse<SurveyAnswerDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete_survey_response')")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"surveyResponse"}));
        return ResponseEntity.ok(apiResponse);
    }

}
