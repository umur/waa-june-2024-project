package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.AbstractPersistableEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "system_action")
public class SystemAction  extends AbstractPersistableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    public SystemAction(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public static class User {
    }
}
