package universityconnect.dto;

import lombok.Data;
import java.util.List;

@Data
public class SurveyStudentDTO {
    private Long id;
    private Long surveyId;
    private Long studentId;
    private List<Long> answerIds;

    public SurveyStudentDTO() {}

    public SurveyStudentDTO(Long id, Long surveyId, Long studentId, List<Long> answerIds) {
        this.id = id;
        this.surveyId = surveyId;
        this.studentId = studentId;
        this.answerIds = answerIds;
    }
}

