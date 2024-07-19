package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class DiscussionThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private Discussion discussion;

    @ManyToMany
    @JoinTable(name="NestedThread",
            joinColumns = @JoinColumn(name="threadId"),
            inverseJoinColumns = @JoinColumn(name="nestedThreadId"))
    private List<DiscussionThread> nestedThreads;

    private LocalDateTime createdOn;

    public DiscussionThread() {}

}
