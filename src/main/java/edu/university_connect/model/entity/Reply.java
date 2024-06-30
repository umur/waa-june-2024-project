package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
}
