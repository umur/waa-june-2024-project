package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "blocker_user_id")
    private User blockerUser;

    @ManyToOne
    @JoinColumn(name = "blocked_user_id")
    private User blockedUser;

    public Block(){}
}
