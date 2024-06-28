package miu.waa.project1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String avatar;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Interest> interests = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ExtracurricularActivity> extracurricular_activities = new ArrayList<>();
}
