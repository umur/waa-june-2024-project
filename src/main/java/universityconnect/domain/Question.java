package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<Answer> answers;

    public Question() {}
}
