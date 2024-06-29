package universityconnect.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private List<Long> answerIds;

    public QuestionDTO() {}

    public QuestionDTO(Long id, String title, List<Long> answerIds) {
        this.id = id;
        this.title = title;
        this.answerIds = answerIds;
    }
}

