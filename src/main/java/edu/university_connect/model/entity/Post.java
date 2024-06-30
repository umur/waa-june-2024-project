package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    @JoinColumn(name="post_id")
    private List<Reply> replies;

    @OneToMany
    @JoinColumn(name="post_id")
    private  List<ModerationReport> moderationReports;


}
