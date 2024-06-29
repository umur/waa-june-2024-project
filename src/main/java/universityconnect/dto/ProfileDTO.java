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

    public ProfileDTO() {}

    public ProfileDTO(Long id, List<String> achievements, List<String> interests, List<String> activities, Long userId) {
        this.id = id;
        this.achievements = achievements;
        this.interests = interests;
        this.activities = activities;
        this.userId = userId;
    }
}
