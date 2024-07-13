package edu.university_connect.domain.entity.discussionthread;

import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String title;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Post> posts;

}
