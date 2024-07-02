package com.waa.project.controller;

import com.waa.project.service.DiscussionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/DiscussionCategory")
public class DiscussionCategoryController {

    @Autowired
    private DiscussionCategoryService discussionCategoryService;

    @GetMapping()
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(discussionCategoryService.getDiscussionCategories());
    }
}
