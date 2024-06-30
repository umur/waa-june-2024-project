package edu.university_connect.domain.entity;

import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role")
    private Set<Role> roles;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Profile profile;

//    @OneToMany(mappedBy = "user")
//    private List<Post> posts;

//    @OneToMany(mappedBy = "user")
//    private List<Reply> replies;

//    @OneToMany(mappedBy="user")
//    private  List<Resource> resources;

//    @OneToMany(mappedBy = "creator")
//    private  List<Survey> createdSurveys;

    @ManyToMany
    @JoinTable(
            name = "user_block",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_user_id")
    )
    private List<User> blockedUsers;



    public User(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
