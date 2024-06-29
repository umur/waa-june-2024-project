package universityconnect.dto;

import lombok.Data;

@Data
public class ResourceDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private Long resourceCategoryId;

    public ResourceDTO() {}

    public ResourceDTO(Long id, String fileName, String filePath, Long resourceCategoryId) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.resourceCategoryId = resourceCategoryId;
    }
}
