package edu.university_connect.model.contract.dto;

import edu.university_connect.domain.entity.User;
import edu.university_connect.domain.entity.discussionthread.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ModerationReportDto {
    private Long id;
    private String reason;
    private UserDto reporter;
    private PostDto post;
    private boolean actionTaken;
    private String actionComment;
    LocalDateTime createdAt;
    String createdBy;
    LocalDateTime lastModifiedAt;
    String lastModifiedBy;
}
