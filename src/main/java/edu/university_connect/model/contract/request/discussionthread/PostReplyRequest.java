package edu.university_connect.model.contract.request.discussionthread;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostReplyRequest extends ReplyRequest {
    @NotNull private Long postId;
}
