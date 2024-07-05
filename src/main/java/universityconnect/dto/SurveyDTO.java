package universityconnect.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyDTO {
    private Long id;
    private String type;
    private String title;
    private List<Long> questionIds;

    public SurveyDTO() {
    }

}

