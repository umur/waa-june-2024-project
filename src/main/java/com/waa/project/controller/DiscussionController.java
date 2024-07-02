package com.waa.project.controller;

import com.waa.project.dto.DiscussionDto;
import com.waa.project.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/student/discussion")
    public ResponseEntity<?> getStudent(Pageable pageable) {

        return ResponseEntity.ok(discussionService.getDiscussions(pageable));
    }

    @GetMapping("/student/discussion/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable long id) {

        return ResponseEntity.ok(discussionService.getDiscussionById(id));
    }

    @PostMapping("/student/discussion")
    public ResponseEntity<?> saveStudent(@RequestBody DiscussionDto discussionDto) {

        return ResponseEntity.ok(discussionService.createDiscussion(discussionDto));
    }

    @PutMapping("/student/discussion/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody DiscussionDto discussionDto, @PathVariable long id) {
        return ResponseEntity.ok(discussionService.updateDiscussion(id, discussionDto));
    }
}
