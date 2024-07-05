package universityconnect.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyStudentDTO {
    private Long id;
    private Long surveyId;
    private Long studentId;
    private List<Long> answerIds;

}

