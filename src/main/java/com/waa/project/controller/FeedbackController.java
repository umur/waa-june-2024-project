package com.waa.project.controller;

import com.waa.project.dto.FeedbackDto;
import com.waa.project.service.FeedbackService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<FeedbackDto> getAllFeedBack() {

        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{catId}")
    public FeedbackDto getFeedBack(@PathVariable Long catId) {
        return feedbackService.getFeedback(catId);
    }

    @PutMapping("/{catId}")
    public String update(
            @RequestBody FeedbackDto feedbackDto,
            @PathVariable Long catId
                        ) {
        return feedbackService.update(feedbackDto, catId);
    }

    @PostMapping
    public String save(@RequestBody FeedbackDto feedbackDto) {
        return feedbackService.save(feedbackDto);
    }

    @DeleteMapping("/{catId}")
    public String delete(@PathVariable Long catId) {

        return feedbackService.delete(catId);
    }


}
