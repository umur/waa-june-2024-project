package universityconnect.dto;

import lombok.Data;

@Data
public class DiscussionCategoryDTO {
    private Long id;
    private String name;
    private String description;

    public DiscussionCategoryDTO() {}

    public DiscussionCategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

