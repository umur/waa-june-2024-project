package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.AbstractPersistableEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "system_role")
public class SystemRole extends AbstractPersistableEntity {

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

    public SystemRole(String name, String code, String description, Set<String> actions) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.actions = actions;
    }
}