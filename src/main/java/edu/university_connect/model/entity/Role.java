package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.MetaData;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Type(JsonType.class)
    @Column(name = "actions", columnDefinition = "json")
    private Set<String> actions;

    public Role(String name, String code, String description, Set<String> actions) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.actions = actions;
    }
}