package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "discussion_comments")
public class DiscussionComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comments", nullable = false)
    private String comment;

    @ManyToOne
    private Discussion discussion;

    @ManyToOne
    private Student student;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private DiscussionComments parentCommentId;

    public void setStudent(Long studentId) {
        Student s = new Student();
        s.setId(studentId);

        this.student = s;
    }
}
