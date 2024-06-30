package edu.miu.cs545.project.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ThreadPost {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private  String title;

    @Column( updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private  Category  category;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private  User user;


    // Callback methods for title and createdAt
    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.title != null) {
            this.title = this.title.trim().toLowerCase();
        }

    }

    @PreUpdate
    private void preUpdate() {
        if (this.title != null) {
            this.title = this.title.trim().toLowerCase();
        }
    }
}
