package com.waa.project.controller.discussion;

import com.waa.project.dto.DiscussionCommentsDto;
import com.waa.project.dto.DiscussionDto;
import com.waa.project.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/students/discussion")
    public ResponseEntity<Page<?>> getDiscussion(Pageable pageable, @AuthenticationPrincipal User user) {

        Page<DiscussionDto> response =discussionService.getDiscussions(pageable, user);
        response.map( data -> {
            if (data.getStudent().getUsername().equals(user.getUsername())) {
                data.setOwn(true);
            } else {
                data.setOwn(false);
            }return  data;
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students/discussion/{id}")
    public ResponseEntity<?> getDiscussionById(@PathVariable long id, @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(discussionService.getDiscussionById(id, user));
    }

    @PostMapping("/students/discussion")
    public ResponseEntity<?> saveDiscussion(
            @RequestBody DiscussionDto discussionDto,
            @AuthenticationPrincipal User user
                                           ) {

        return ResponseEntity.ok(discussionService.createDiscussion(discussionDto, user));
    }

    @PutMapping("/students/discussion/{id}")
    public ResponseEntity<?> updateDiscussion(
            @RequestBody DiscussionDto discussionDto,
            @PathVariable("id") long id,
            @AuthenticationPrincipal User user
                                             ) {
        return ResponseEntity.ok(discussionService.updateDiscussion(id, discussionDto, user));
    }

    @DeleteMapping("/students/discussion/{id}")
    public ResponseEntity<?> deleteDiscussion(@PathVariable("id") long id, @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(discussionService.deleteDiscussion(id, user));
    }

    @GetMapping("/students/discussion/search")
    public ResponseEntity<?> searchDiscussion(Pageable pageable,@RequestParam String text) {
        return ResponseEntity.ok(discussionService.searching(pageable, text));
    }
}
