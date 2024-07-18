package edu.university_connect.model.contract.request.report;

import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import lombok.Data;

@Data
public class ModerationReportRequest {
    private String reason;
    private Post post;
}
