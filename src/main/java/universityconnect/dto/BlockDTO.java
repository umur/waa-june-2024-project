package universityconnect.dto;

import lombok.Data;

@Data
public class BlockDTO {
    private Long id;
    private String name;

    public BlockDTO() {}

    public BlockDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

