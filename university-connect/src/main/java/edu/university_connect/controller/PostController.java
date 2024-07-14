package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.dto.ReplyDto;
import edu.university_connect.model.contract.request.RequestUtils;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.post.PostService;
import edu.university_connect.service.reply.ReplyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ReplyService replyService;
    private final MessagingService messagingService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('view_post_list')")
    public ResponseEntity<ApiResponse<List<PostDto>>> getAll() {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "post");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, postService.getAll()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_post_list')")
    public ResponseEntity<ApiResponse<Page<PostDto>>> getPage(Pageable pageable) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "post");
        return ResponseEntity.ok(ApiResponse.of(
                responseMessage,
                postService.getPage(RequestUtils.extractPagination(pageable)))
        );
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('view_post_list')")
    public ResponseEntity<ApiResponse<Page<PostDto>>> getPage(@RequestParam String term, Pageable pageable) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "post");
        return ResponseEntity.ok(ApiResponse.of(
                responseMessage,
                postService.search(term, RequestUtils.extractPagination(pageable)))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('view_post')")
    public ResponseEntity<ApiResponse<PostDto>> getById(@PathVariable Long id) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "post");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, postService.getById(id)));
    }

    @GetMapping("/{id}/replies")
    @PreAuthorize("hasAuthority('view_reply_list')")
    public ResponseEntity<ApiResponse<List<ReplyDto>>> getByPostId(@PathVariable("id") Long id) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "reply");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, replyService.getByPostId(id)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete_post')")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response = postService.delete(id);
        String message = messagingService.getResponseMessage(AppStatusCode.S20005, "post");
        return ResponseEntity.ok(ApiResponse.of(message, response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('modify_post')")
    public ResponseEntity<ApiResponse<PostDto>> update(@Valid @RequestBody PostRequest postRequest,
                                                        @PathVariable Long id) {
        PostDto updatedPost = postService.update(id, postRequest);
        String responseMessage = messagingService.getResponseMessage(AppStatusCode.S20004, "post");
        return ResponseEntity.ok(ApiResponse.of(responseMessage, updatedPost));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_post')")
    public ResponseEntity<ApiResponse<PostDto>> create(@Valid @RequestBody PostRequest postRequest) {
        PostDto postDto = postService.create(postRequest);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, "post");
        return ResponseEntity.ok(ApiResponse.of(message, postDto));
    }
}
