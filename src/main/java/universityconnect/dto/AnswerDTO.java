package universityconnect.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String answer;

    public AnswerDTO() {}

    public AnswerDTO(Long id, String answer) {
        this.id = id;
        this.answer = answer;
    }
}

