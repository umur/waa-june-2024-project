package com.waa.project.controller.feedback;

import com.waa.project.dto.FeedbackCategoryDto;
import com.waa.project.dto.requests.FeedbackCategoryRequest;
import com.waa.project.dto.responses.FeedbackCategoryResponse;
import com.waa.project.entity.FeedbackCategory;
import com.waa.project.service.FeedbackCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1")
public class FeedbackCategoryController {

    @Autowired
    private FeedbackCategoryService feedbackCategoryService;

    @GetMapping("/feedbackcategories")
    public List<FeedbackCategoryDto> getAllFeedCategory() {
        return feedbackCategoryService.getAllCategories();
    }

    @GetMapping("/feedbackcategories/{catId}")
    public FeedbackCategoryDto getAllFeedCategory(@PathVariable Long catId) {
        return feedbackCategoryService.getCategory(catId);
    }

    @PutMapping("/admins/feedbackcategories/{catId}")
    public String update(
            @Valid @RequestBody FeedbackCategoryDto feedbackCategoryDto,
            @PathVariable Long catId
    ) {
        return feedbackCategoryService.update(feedbackCategoryDto, catId);
    }

    @PostMapping("/admins/feedbackcategories")
    public ResponseEntity<FeedbackCategoryResponse> saveFeedbackCategory(
            @Valid @RequestBody FeedbackCategoryRequest feedbackCategory) {
        return new ResponseEntity<>(feedbackCategoryService.save(feedbackCategory), HttpStatus.CREATED);
    }

    @DeleteMapping("/admins/feedbackcategories/{catId}")
    public List<FeedbackCategory> delete(@PathVariable Long catId) {
        return feedbackCategoryService.delete(catId);
    }


}
