package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Post> posts;

}
