package com.waa.project.controller.feedback;

import com.waa.project.dto.FeedbackDto;
import com.waa.project.service.FeedbackService;
import com.waa.project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @GetMapping("/feedbacks")
    public List<FeedbackDto> getAllFeedBack() {

        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/feedbacks/{id}")
    public FeedbackDto getFeedBack(@PathVariable Long id) {
        return feedbackService.getFeedback(id);
    }

    @GetMapping("/feedbacks/search")
    public List<FeedbackDto> search(@RequestParam String search) {
        return feedbackService.search(search);
    }

    @GetMapping("/feedbacks/category/{Id}")
    public List<FeedbackDto> getFeedByCategory(@PathVariable Long Id) {
        return feedbackService.findFeedbackByCategory(Id);
    }

    @PutMapping({"/admins/feedbacks/{Id}", "/students/feedbacks/{Id}"})
    public String update(
            @RequestBody FeedbackDto feedbackDto,
            @PathVariable Long Id,
            @AuthenticationPrincipal UserDetails userDetails
                        ) {
        Long currentUser = null;

        if (userDetails != null && userDetails.getUsername() != null) {
            currentUser = userService.findByUsername(userDetails.getUsername()).getId();
        }

        return feedbackService.update(feedbackDto, Id, currentUser);
    }

    @PostMapping("/feedbacks")
    public String save(@RequestBody FeedbackDto feedbackDto, @AuthenticationPrincipal UserDetails userDetails) {
        Long currentUser = null;

        if (userDetails != null && userDetails.getUsername() != null) {
            currentUser = userService.findByUsername(userDetails.getUsername()).getId();
        }
        return feedbackService.save(feedbackDto, currentUser);
    }

    @DeleteMapping({"/admins/feedbacks/{Id}", "/students/feedbacks/{Id}"})
    public String delete(@PathVariable Long Id) {

        return feedbackService.delete(Id);
    }

    @GetMapping("/reports")
    public Map<String, Integer> findFeedbackByCategoryCount() {
        List<Object[]> results = feedbackService.findFeedbackByCategoryCount();
        return results.stream()
                      .collect(Collectors.toMap(
                              result -> (String) result[0],
                              result -> ((Long) result[1]).intValue()
                                               ));

    }

    @GetMapping("/feedbacks/showMe")
    public Map<String, String> showMe(@AuthenticationPrincipal UserDetails userDetails) {
        var                 result   = userService.findByUsername(userDetails.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("name", result.getUsername());
        response.put("email", result.getUsername());
        response.put("role", result.getUsername());
        response.put("id", result.getId().toString());
        return response;

    }
}
