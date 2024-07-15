package com.waa.project.repository;

import com.waa.project.entity.DiscussionComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscussionCommentsRepository extends JpaRepository<DiscussionComments, Long> {

    Page<DiscussionComments> findByDiscussionId(Long discussionId, Pageable pageable);

    Optional<DiscussionComments> findByIdAndStudentId(Long id, Long studentId);

    void deleteByIdAndStudentId(long id, long studentId);

    @Query(
            value = "select * from discussion_comments c where c.parent_comment_id= :parentCommentId",
            nativeQuery = true
    )
    Page<DiscussionComments> findByParentCommentId(Long parentCommentId, Pageable pageable);

    void deleteAllByParentCommentId(DiscussionComments id);

    List<DiscussionComments> findAllByDiscussionId(Long discussionId);

    @Modifying
    @Query("DELETE FROM DiscussionComments dc WHERE dc.parentCommentId.id IN :parentCommentIds")
    void deleteAllByParentCommentIds(@Param("parentCommentIds") List<Long> parentCommentIds);

    void deleteByDiscussionId(Long discussionId);

}
