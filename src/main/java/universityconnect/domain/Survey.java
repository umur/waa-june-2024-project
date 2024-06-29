package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String title;

    @OneToMany
    @JoinColumn(name = "survey_id")
    private List<Question> questions;
}
