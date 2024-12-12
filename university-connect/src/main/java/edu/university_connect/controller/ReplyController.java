package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.ReplyDto;
import edu.university_connect.model.contract.request.discussionthread.PostReplyRequest;
import edu.university_connect.model.contract.request.discussionthread.ReplyReplyRequest;
import edu.university_connect.model.contract.request.discussionthread.ReplyRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.reply.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@CrossOrigin
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final MessagingService messagingService;

    @GetMapping
    @PreAuthorize("hasAuthority('view_reply_list')")
    public ResponseEntity<ApiResponse<List<ReplyDto>>> getAll() {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "reply");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, replyService.getAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('view_reply')")
    public ResponseEntity<ApiResponse<ReplyDto>> getById(@PathVariable Long id) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "reply");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, replyService.getById(id)));
    }

    @GetMapping("/{id}/replies")
    @PreAuthorize("hasAuthority('view_reply')")
    public ResponseEntity<ApiResponse<List<ReplyDto>>> getByReplyId(@PathVariable("id") Long replyId) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "reply");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, replyService.getByReplyId(replyId)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete_reply')")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response = replyService.delete(id);
        String message = messagingService.getResponseMessage(AppStatusCode.S20005, "reply");
        return ResponseEntity.ok(ApiResponse.of(message, response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('modify_reply')")
    public ResponseEntity<ApiResponse<ReplyDto>> update(@Valid @RequestBody ReplyRequest replyRequest,
                                                        @PathVariable Long id) {
        ReplyDto updatedReply = replyService.update(id, replyRequest);
        String responseMessage = messagingService.getResponseMessage(AppStatusCode.S20004, "reply");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, updatedReply));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_reply')")
    public ResponseEntity<ApiResponse<ReplyDto>> create(@Valid @RequestBody PostReplyRequest replyRequest) {
        ReplyDto replyDto = replyService.create(replyRequest);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, "reply");
        return ResponseEntity.ok(ApiResponse.of(message, replyDto));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('create_reply')")
    public ResponseEntity<ApiResponse<ReplyDto>> createReplyToReply(
            @PathVariable Long id,
            @Valid @RequestBody ReplyRequest replyRequest
    ) {
        ReplyReplyRequest replyReplyRequest = new ReplyReplyRequest();
        replyReplyRequest.setToReplyId(id);
        replyReplyRequest.setContent(replyRequest.getContent());
        ReplyDto replyDto = replyService.addReplyToReply(replyReplyRequest);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, "reply");
        return ResponseEntity.ok(ApiResponse.of(message, replyDto));
    }
}
