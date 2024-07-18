package com.waa.project.dto.responses;

import com.waa.project.entity.FeedbackCategory;
import com.waa.project.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    private Long id;
    private String title;
    private String body;
    private FeedbackCategory feedbackCategory;
    private Student student;
}
