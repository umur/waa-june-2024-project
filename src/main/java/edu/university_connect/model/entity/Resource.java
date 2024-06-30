package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Resource extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
