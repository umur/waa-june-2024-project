package com.waa.project.dto;

import com.waa.project.entity.FeedbackCategory;
import com.waa.project.entity.Student;
import lombok.Data;

@Data
public class FeedbackDto {
    private Long             id;
    private String           title;
    private String           body;
    private FeedbackCategory feedbackCategory;
    private Student          student;
}
