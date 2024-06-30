package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ModerationReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;


}
