package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reportedId;

    public Report() {}

}
