package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Reply extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(length = 5000,nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
