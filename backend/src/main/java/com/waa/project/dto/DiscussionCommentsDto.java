package com.waa.project.dto;

import lombok.Data;

@Data
public class DiscussionCommentsDto {

    private Long   id;
    private String comment;
    private StudentInfo student;
    private Long   discussionId;
    private Long   parentCommentIdId;
    private boolean own;
    private Long   subComments;
}
