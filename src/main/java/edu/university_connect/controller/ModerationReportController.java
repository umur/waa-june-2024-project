package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.ModerationReportDto;
import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import edu.university_connect.model.contract.request.report.ModerationReportRequest;
import edu.university_connect.model.contract.request.report.ReportUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.report.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin
@RequiredArgsConstructor
public class ModerationReportController {
    private final ReportService moderationService;
    private final MessagingService messagingService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<ModerationReportDto>>> getAll() {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "report");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, moderationService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ModerationReportDto>> create(@Valid @RequestBody ModerationReportRequest request) {
        ModerationReportDto report = moderationService.createReport(request);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, "moderation report");
        return ResponseEntity.ok(ApiResponse.of(message, report));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ModerationReportDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ReportUpdateRequest request) {
        ModerationReportDto report = moderationService.update(id, request);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, "moderation report");
        return ResponseEntity.ok(ApiResponse.of(message, report));
    }
}
