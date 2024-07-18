package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DiscussionCategory category;

    @OneToMany(mappedBy = "discussion",cascade = CascadeType.REMOVE)
    private List<DiscussionThread> discussionThreads;

    @ManyToOne
    private User user;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public Discussion(){}

}
