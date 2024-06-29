package universityconnect.domain;
import jakarta.persistence.*;

@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Block(){}
}
