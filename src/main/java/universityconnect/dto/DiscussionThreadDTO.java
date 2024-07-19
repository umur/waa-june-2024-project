package universityconnect.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiscussionThreadDTO {
    private Long id;
    private String comment;
    private Long userId;
    private Long discussionId;
    private String username;
    private List<Long> nestedThreadIds;
    private LocalDateTime createdOn;

    public DiscussionThreadDTO() {}

    public DiscussionThreadDTO(Long id, String comment, Long userId, String username, Long discussionId, List<Long> nestedThreadIds,LocalDateTime createdOn) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.discussionId = discussionId;
        this.nestedThreadIds = nestedThreadIds;
        this.username = username;
        this.createdOn = createdOn;
    }
}

