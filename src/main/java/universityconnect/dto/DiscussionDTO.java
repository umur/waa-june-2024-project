package universityconnect.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiscussionDTO {
    private Long id;
    private String topic;
    private String description;
    private Long categoryId;
    private List<Long> discussionThreadIds;
    private Long userId;
    private String username;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public DiscussionDTO() {}

    public DiscussionDTO(Long id, String topic, Long categoryId, List<Long> discussionThreadIds, Long userId, LocalDateTime createdOn, LocalDateTime updatedOn,String username) {
        this.id = id;
        this.topic = topic;
        this.categoryId = categoryId;
        this.discussionThreadIds = discussionThreadIds;
        this.userId = userId;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.username = username;
    }
}
