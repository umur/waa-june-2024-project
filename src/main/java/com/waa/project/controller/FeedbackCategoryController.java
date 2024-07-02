package com.waa.project.controller;

import com.waa.project.dto.FeedbackCategoryDto;
import com.waa.project.service.FeedbackCategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1/feedbackcategories")
public class FeedbackCategoryController {

    @Autowired
    private FeedbackCategoryService feedbackCategoryService;

    @GetMapping
    public List<FeedbackCategoryDto> getAllFeedCategory() {
        return feedbackCategoryService.getAllCategories();
    }

    @GetMapping("/{catId}")
    public FeedbackCategoryDto getAllFeedCategory(@PathVariable Long catId) {
        return feedbackCategoryService.getCategory(catId);
    }

    @PutMapping("/admin/{catId}")
    public String getAllFeedCategory(
            @RequestBody FeedbackCategoryDto feedbackCategoryDto,
            @PathVariable Long catId
                                    ) {
        return feedbackCategoryService.update(feedbackCategoryDto, catId);
    }

    @PostMapping("/admin")
    public String getAllFeedCategory(@RequestBody FeedbackCategoryDto feedbackCategoryDto) {
        return feedbackCategoryService.save(feedbackCategoryDto);
    }

    @DeleteMapping("/admin/{catId}")
    public String delete(@PathVariable Long catId) {
        return feedbackCategoryService.delete(catId);
    }


}
