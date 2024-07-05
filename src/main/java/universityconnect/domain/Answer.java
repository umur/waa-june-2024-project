package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;

    public Answer(){}
}
