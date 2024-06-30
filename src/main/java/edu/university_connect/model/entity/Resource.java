package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String url;

}
