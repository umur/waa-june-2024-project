package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DiscussionCategory category;

    @OneToMany(mappedBy = "discussion")
    private List<DiscussionThread> discussionThreads;

    @ManyToOne
    private User user;

    public Discussion(){}

}
