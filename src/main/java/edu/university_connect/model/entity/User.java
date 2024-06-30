package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Student student;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Reply> replies;

    @ManyToMany(mappedBy = "user")
    private List<Role> roles;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany
    @JoinColumn(name="user_id")
    private  List<Resource> resources;

    @OneToMany(mappedBy = "creator")
    private  List<Survey> createdSurveys;

    @ManyToMany
    @JoinTable(
            name = "user_event_attendance",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @ManyToMany
    @JoinTable(
            name = "user_block",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_user_id")
    )
    private List<User> blockedUsers;
}
