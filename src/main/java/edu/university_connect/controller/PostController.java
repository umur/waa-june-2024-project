package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.dto.ReplyDto;
import edu.university_connect.model.contract.request.discussionthread.PostReplyRequest;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MessagingService messagingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAll() {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "post");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, postService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getById(@PathVariable Long id) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "post");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, postService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response = postService.delete(id);
        String message = messagingService.getResponseMessage(AppStatusCode.S20005, "post");
        return ResponseEntity.ok(ApiResponse.of(message, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> update(@Valid @RequestBody PostRequest postRequest,
                                                        @PathVariable Long id) {
        PostDto updatedPost = postService.update(id, postRequest);
        String responseMessage = messagingService.getResponseMessage(AppStatusCode.S20004, "post");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, updatedPost));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> create(@Valid @RequestBody PostRequest postRequest) {
        PostDto postDto = postService.create(postRequest);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, "post");
        return ResponseEntity.ok(ApiResponse.of(message, postDto));
    }
}
