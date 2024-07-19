package universityconnect.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProfileDTO {
    private Long id;
    private List<String> achievements;
    private List<String> interests;
    private List<String> activities;
    private Long userId;
    private String username;

    public ProfileDTO() {}
}
