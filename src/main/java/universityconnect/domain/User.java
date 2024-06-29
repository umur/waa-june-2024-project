package universityconnect.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String address;
    @Embedded
    private AuditData auditData;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Block> blocks;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Report> reportedLists;

    public abstract Role getRole();
}
