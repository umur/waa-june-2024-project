package edu.university_connect.domain.entity;

import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ModerationReport extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private boolean actionTaken;

    private String actionComment;


}
