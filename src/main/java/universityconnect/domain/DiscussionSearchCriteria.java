package universityconnect.domain;

import lombok.Data;

@Data
public class DiscussionSearchCriteria {
    private String keyword;
    private String topic;
    private String categoryName;
}
