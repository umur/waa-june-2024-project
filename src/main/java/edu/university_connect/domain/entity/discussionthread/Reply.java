package edu.university_connect.domain.entity.discussionthread;

import edu.university_connect.domain.entity.User;
import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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
    private Reply replyThread;

    @OneToMany(mappedBy = "replyThread", cascade = CascadeType.ALL)
    private Set<Reply> replies = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @PreRemove
    private void preRemove() {
        for (Reply r : getReplies()) {
            r.setReplyThread(null);
        }
    }
}
