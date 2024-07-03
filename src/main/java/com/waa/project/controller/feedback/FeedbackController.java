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
            System.out.println("uuuu ===" + userDetails.getUsername());
            System.out.println("uuuu ===" + userDetails.getAuthorities());

            currentUser = userService.findByUsername(userDetails.getUsername()).getId();

        }
        System.out.println("currentUser ===" + currentUser);

        return feedbackService.update(feedbackDto, Id, currentUser);
    }

    @PostMapping("/feedbacks")
    public String save(@RequestBody FeedbackDto feedbackDto, @AuthenticationPrincipal UserDetails userDetails) {
        Long currentUser = null;

        if (userDetails != null && userDetails.getUsername() != null) {
            currentUser = userService.findByUsername(userDetails.getUsername()).getId();
        }

        System.out.println("userId ===" + currentUser);

        return feedbackService.save(feedbackDto, currentUser);
    }

    @DeleteMapping({"/admins/feedbacks/{Id}", "/students/feebacks/{Id}"})
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

}
