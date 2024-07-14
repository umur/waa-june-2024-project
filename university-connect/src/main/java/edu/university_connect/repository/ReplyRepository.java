package edu.university_connect.repository;

import edu.university_connect.domain.entity.discussionthread.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    String REPLY_PROJECTIONS = "SELECT r.id, r.created_at, r.created_by, r.last_modified_at, " +
            "r.last_modified_by, r.content, r.post_id, r.user_id, r.reply_thread_id ";

    String SELECT_UNBLOCKED_REPLY_QUERY = REPLY_PROJECTIONS +
            "FROM Reply r INNER JOIN User u ON r.user_id = u.id " +
            "WHERE NOT EXISTS (SELECT ub.user_id FROM user_block ub WHERE ub.blocked_user_id = r.user_id) " +
            "AND r.id = ?";

    String SELECT_ALL_UNBLOCKED_REPLIES_QUERY = REPLY_PROJECTIONS +
            "FROM Reply r INNER JOIN User u ON r.user_id = u.id " +
            "WHERE NOT EXISTS (SELECT ub.user_id FROM user_block ub WHERE ub.blocked_user_id = r.user_id)";

    String SELECT_REPLIES_BY_POST_QUERY = REPLY_PROJECTIONS +
            "FROM Reply r INNER JOIN User u ON r.user_id = u.id " +
            "WHERE NOT EXISTS (SELECT ub.user_id FROM user_block ub WHERE ub.blocked_user_id = r.user_id) " +
            "AND r.post_id = ? " +
            "AND reply_thread_id IS NULL";

    String SELECT_REPLIES_BY_REPLY_QUERY = REPLY_PROJECTIONS +
            "FROM Reply r INNER JOIN User u ON r.user_id = u.id " +
            "WHERE NOT EXISTS (SELECT ub.user_id FROM user_block ub WHERE ub.blocked_user_id = r.user_id) " +
            "AND r.reply_thread_id = ?";

    @Query(value = SELECT_UNBLOCKED_REPLY_QUERY, nativeQuery = true)
    Optional<Reply> findUnblockedReply(Long id);

    @Query(value = SELECT_ALL_UNBLOCKED_REPLIES_QUERY, nativeQuery = true)
    List<Reply> findAllUnblockedReplies();

    @Query(value = SELECT_REPLIES_BY_POST_QUERY, nativeQuery = true)
    List<Reply> findByPostIdAndReplyThreadNull(Long postId);

    @Query(value = SELECT_REPLIES_BY_REPLY_QUERY, nativeQuery = true)
    List<Reply> findByReplyThreadId(Long replyId);
}
