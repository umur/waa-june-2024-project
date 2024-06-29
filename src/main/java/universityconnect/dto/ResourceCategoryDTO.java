package universityconnect.dto;

import lombok.Data;

@Data
public class ResourceCategoryDTO {
    private Long id;
    private String name;

    public ResourceCategoryDTO() {}

    public ResourceCategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

