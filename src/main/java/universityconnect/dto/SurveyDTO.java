package universityconnect.dto;

import lombok.Data;
import java.util.List;

@Data
public class SurveyDTO {
    private Long id;
    private String type;
    private String title;
    private List<Long> questionIds;

    public SurveyDTO() {}

    public SurveyDTO(Long id, String type, String title, List<Long> questionIds) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.questionIds = questionIds;
    }
}

