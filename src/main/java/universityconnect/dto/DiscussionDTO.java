package universityconnect.dto;

import lombok.Data;
import java.util.List;

@Data
public class DiscussionDTO {
    private Long id;
    private String topic;
    private String description;
    private Long categoryId;
    private List<Long> discussionThreadIds;
    private Long userId;

    public DiscussionDTO() {}

    public DiscussionDTO(Long id, String topic, Long categoryId, List<Long> discussionThreadIds, Long userId) {
        this.id = id;
        this.topic = topic;
        this.categoryId = categoryId;
        this.discussionThreadIds = discussionThreadIds;
        this.userId = userId;
    }
}
