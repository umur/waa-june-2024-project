package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ResourceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public ResourceCategory() {}

}
