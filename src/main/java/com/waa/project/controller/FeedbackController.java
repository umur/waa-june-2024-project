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
@RequestMapping("/api/v1/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<FeedbackDto> getAllFeedBack() {

        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{Id}")
    public FeedbackDto getFeedBack(@PathVariable Long Id) {
        return feedbackService.getFeedback(Id);
    }

    @PutMapping("/{Id}")
    public String update(
            @RequestBody FeedbackDto feedbackDto, @PathVariable Long Id
                        ) {
        return feedbackService.update(feedbackDto, Id);
    }

    @PostMapping
    public String save(@RequestBody FeedbackDto feedbackDto) {
        return feedbackService.save(feedbackDto);
    }

    @DeleteMapping("/{Id}")
    public String delete(@PathVariable Long Id) {

        return feedbackService.delete(Id);
    }


}
