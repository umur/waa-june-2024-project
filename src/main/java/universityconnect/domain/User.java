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

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Block> blocks;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Report> reportedLists;

    @OneToMany(mappedBy = "user")
    private List<Discussion> discussions;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<Resource> resources;

    @OneToMany(mappedBy = "organizer")
    private List<Event> events;

    @Enumerated
    private Role role;

    public abstract Role getRole();
}
