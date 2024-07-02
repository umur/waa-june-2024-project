package universityconnect.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User {

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

    @OneToMany(mappedBy = "blockerUser")
    private List<Block> blockerUserLists;

    @OneToMany(mappedBy = "blockedUser")
    private List<Block> blockedUserLists;

    @OneToMany(mappedBy = "reporterUser")
    private List<Report> reporterUserLists;

    @OneToMany(mappedBy = "reportedUser")
    private List<Report> reportedUserLists;

    @OneToMany(mappedBy = "user")
    private List<Discussion> discussions;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<Resource> resources;

    @OneToMany(mappedBy = "organizer")
    private List<Event> events;

    @Enumerated
    private Role role;

}
