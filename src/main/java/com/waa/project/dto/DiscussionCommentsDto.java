package com.waa.project.dto;

import lombok.Data;

@Data
public class DiscussionCommentsDto {

    private Long   id;
    private String comment;
    private String studentUsername;
    private String studentFirstName;
    private String studentLastName;
    private Long   discussionId;
}
