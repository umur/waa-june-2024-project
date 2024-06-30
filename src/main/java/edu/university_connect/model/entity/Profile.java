package edu.university_connect.model.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.Set;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(JsonType.class)
    @Column(name = "achievements", columnDefinition = "json")
    private Set<AcademicAchievement> achievements;

    @Type(JsonType.class)
    @Column(name = "interests", columnDefinition = "json")
    private Set<Interest> interests;

    @Type(JsonType.class)
    @Column(name = "extraCurricularActivities", columnDefinition = "json")
    private Set<ExtraCurricularActivity> extraCurricularActivities;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private  User user;
}
