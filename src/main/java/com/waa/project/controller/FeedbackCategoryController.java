package com.waa.project.controller;

import com.waa.project.entity.FeedbackCategory;
import com.waa.project.service.FeedbackCategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/feedbackcategories")
public class FeedbackCategoryController {

    @Autowired
    private FeedbackCategoryService feedbackCategoryService;

    @GetMapping
    public List<FeedbackCategory> getAllFeedCategory() {
        return feedbackCategoryService.getAllCategories();
    }

    @GetMapping("/{catId}")
    public FeedbackCategory getAllFeedCategory(@PathVariable Long catId) {
        return feedbackCategoryService.getCategory(catId);
    }

    @PutMapping("/{catId}")
    public String getAllFeedCategory(
            @RequestBody FeedbackCategory feedbackCategoryDto,
            @PathVariable Long catId
                                    ) {
        return feedbackCategoryService.update(feedbackCategoryDto, catId);
    }

    @PostMapping
    public String getAllFeedCategory(@RequestBody FeedbackCategory feedbackCategoryDto) {
        return feedbackCategoryService.save(feedbackCategoryDto);
    }

    @DeleteMapping("/{catId}")
    public String delete(@PathVariable Long catId) {
        return feedbackCategoryService.delete(catId);
    }


}
