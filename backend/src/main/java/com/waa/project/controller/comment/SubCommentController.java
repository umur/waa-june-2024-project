package com.waa.project.controller.comment;

import com.waa.project.dto.DiscussionCommentsDto;
import com.waa.project.service.SubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SubCommentController {

    @Autowired
    private SubCommentService subCommentsService;

    @GetMapping("/students/{commentId}/sub-comments")
    public ResponseEntity<Page<?>> getComments(
            Pageable pageable,
            @AuthenticationPrincipal User user,
            @PathVariable("commentId") long commentId
                                              ) {
        return ResponseEntity.ok(subCommentsService.getSubCommentsByDiscussionId(commentId, pageable));
    }

    @PostMapping("/students/sub-comments")
    public ResponseEntity<?> saveComment(
            @RequestBody DiscussionCommentsDto commentsDto,
            @AuthenticationPrincipal User user
                                        ) {

        return ResponseEntity.ok(subCommentsService.createSubDiscussionComments(commentsDto, user));
    }

    @PutMapping("/students/sub-comments/{id}")
    public ResponseEntity<?> updateComment(
            @RequestBody DiscussionCommentsDto commentsDto,
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user
                                          ) {
        return ResponseEntity.ok(subCommentsService.updateSubDiscussionComments(id, commentsDto, user));
    }

    @DeleteMapping("/students/sub-comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") long id, @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(subCommentsService.deleteSubDiscussionComments(id, user));
    }
}
