package edu.university_connect.domain.entity.discussionthread;

import edu.university_connect.domain.entity.ModerationReport;
import edu.university_connect.domain.entity.User;
import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Post extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(length = 5000,nullable = false)
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    @JoinColumn(name="post_id")
    private List<Reply> replies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private  List<ModerationReport> moderationReports;

}
