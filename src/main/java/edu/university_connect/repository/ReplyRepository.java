package edu.university_connect.repository;

import edu.university_connect.domain.entity.discussionthread.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findByPostIdAndReplyThreadNull(Long postId);
    List<Reply> findByReplyThreadId(Long replyId);
}
