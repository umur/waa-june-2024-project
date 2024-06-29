package universityconnect.dto;

import lombok.Data;
import java.util.List;

@Data
public class DiscussionThreadDTO {
    private Long id;
    private String comment;
    private Long userId;
    private Long discussionId;
    private List<Long> nestedThreadIds;

    public DiscussionThreadDTO() {}

    public DiscussionThreadDTO(Long id, String comment, Long userId, Long discussionId, List<Long> nestedThreadIds) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.discussionId = discussionId;
        this.nestedThreadIds = nestedThreadIds;
    }
}

