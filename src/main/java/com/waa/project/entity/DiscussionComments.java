package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DiscussionComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    private Discussion discussion;

    @ManyToOne
    private Student student;

    @OneToMany
    @JoinTable(name = "subCommentList")
    private List<DiscussionComments> commentsList;
}
