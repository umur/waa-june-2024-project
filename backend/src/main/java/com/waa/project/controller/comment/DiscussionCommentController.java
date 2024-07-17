package com.waa.project.controller.comment;

import com.waa.project.dto.DiscussionCommentsDto;
import com.waa.project.service.DiscussionCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DiscussionCommentController {

    @Autowired
    private DiscussionCommentsService commentsService;

    @GetMapping("/students/{DiscussionId}/comments")
    public ResponseEntity<Page<?>> getComments(
            Pageable pageable,
            @AuthenticationPrincipal User user,
            @PathVariable("DiscussionId") long id
                                              ) {

        Page<DiscussionCommentsDto> response =commentsService.getCommentsByDiscussionId(id, pageable);
        response.map( data -> {
            if (data.getStudent().getUsername().equals(user.getUsername())) {
                data.setOwn(true);
            } else {
                data.setOwn(false);
            }return  data;
        }

        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/students/{DiscussionId}/comments")
    public ResponseEntity<?> saveComment(
            @RequestBody DiscussionCommentsDto commentsDto,
            @PathVariable("DiscussionId") long discussionId,
            @AuthenticationPrincipal User user
                                        ) {

        return ResponseEntity.ok(commentsService.createDiscussionComments(commentsDto, user,discussionId));
    }

    @PutMapping("/students/comments/{id}")
    public ResponseEntity<?> updateComment(
            @RequestBody DiscussionCommentsDto commentsDto,
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user
                                          ) {
        return ResponseEntity.ok(commentsService.updateDiscussionComments(id, commentsDto, user));
    }

    @DeleteMapping("/students/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") long id, @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(commentsService.deleteDiscussionComments(id, user));
    }
}
