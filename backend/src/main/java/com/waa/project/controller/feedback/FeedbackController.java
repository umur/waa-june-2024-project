package com.waa.project.controller.feedback;

import com.waa.project.dto.FeedbackDto;
import com.waa.project.dto.requests.FeedbackRequest;
import com.waa.project.dto.responses.FeedbackResponse;
import com.waa.project.entity.Feedback;
import com.waa.project.service.FeedbackService;
import com.waa.project.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Feedback> getAllFeedBack() {

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

    @GetMapping("/feedbacks/titlewithid/{title}/{id}")
    public List<FeedbackDto> findWithTitle(@PathVariable String title, @PathVariable Long id) {
        return feedbackService.findByTitleAndId(title, id);
    }

    @GetMapping("/feedbacks/category/{Id}")
    public List<FeedbackDto> getFeedByCategory(@PathVariable Long Id) {
        return feedbackService.findFeedbackByCategory(Id);
    }

    @PutMapping({"/feedbacks/{Id}"})//, "/students/feedbacks/{Id}"})
    public ResponseEntity<FeedbackResponse> update(
            @Valid @RequestBody FeedbackRequest feedbackRequest,
            @PathVariable Long Id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        Long currentUser = null;

        if (userDetails != null && userDetails.getUsername() != null) {
            currentUser = userService.findByUsername(userDetails.getUsername()).getId();
        }
        return new ResponseEntity<>(feedbackService.update(feedbackRequest, Id, currentUser), HttpStatus.CREATED);

    }

    @PostMapping("/feedbacks")
    public ResponseEntity<FeedbackResponse> save(
            @Valid @RequestBody FeedbackRequest feedbackRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long currentUser = null;
        if (userDetails != null && userDetails.getUsername() != null) {
            currentUser = userService.findByUsername(userDetails.getUsername()).getId();
        }
        return new ResponseEntity<>(feedbackService.save(feedbackRequest, currentUser), HttpStatus.CREATED);
    }

    @DeleteMapping({"/feedbacks/{Id}"})// "/students/feedbacks/{Id}"})
    public List<Feedback> delete(@PathVariable Long Id) {
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
        System.out.println("userDetails===" + userDetails);
        var result = userService.findByUsername(userDetails.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("name", result.getUsername());
        response.put("email", result.getUsername());
        response.put("role", result.getUsername());
        response.put("id", result.getId().toString());
        return response;
    }
}
