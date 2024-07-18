package edu.university_connect.repository;

import edu.university_connect.domain.entity.discussionthread.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    String POST_PROJECTIONS = "SELECT p.id, p.created_at, p.created_by, p.last_modified_at, " +
            "p.last_modified_by, p.content, p.category_id, p.user_id ";

    String SELECT_UNBLOCKED_POST_QUERY = POST_PROJECTIONS +
            "FROM Post p INNER JOIN User u ON p.user_id = u.id " +
            "WHERE NOT EXISTS (SELECT ub.user_id FROM user_block ub WHERE blocked_user_id = p.user_id) " +
            "AND p.id = ?";

    String SELECT_ALL_UNBLOCKED_POSTS_QUERY = POST_PROJECTIONS +
            "FROM Post p INNER JOIN User u ON p.user_id = u.id " +
            "WHERE NOT EXISTS (SELECT ub.user_id FROM user_block ub WHERE blocked_user_id = p.user_id)";

    String FIND_POSTS_BY_CATEGORY_QUERY = POST_PROJECTIONS +
            "FROM Post p INNER JOIN User u ON p.user_id = u.id " +
            "INNER JOIN Category c ON c.id = p.category_id " +
            "WHERE NOT EXISTS (SELECT ub.user_id FROM user_block ub WHERE blocked_user_id = p.user_id) " +
            "AND p.category_id = ?";

    String SEARCH_POSTS_QUERY = POST_PROJECTIONS +
            "FROM Post p INNER JOIN User u ON p.user_id = u.id " +
            "LEFT JOIN Student s ON s.user_id = u.id " +
            "WHERE NOT EXISTS(SELECT ub.user_id FROM user_block ub WHERE blocked_user_id = p.user_id) " +
            "AND (MATCH (p.content) AGAINST (? IN BOOLEAN MODE) " +
            "OR MATCH(u.username, u.email) AGAINST (? IN BOOLEAN MODE) " +
            "OR MATCH(s.first_name, s.last_name) AGAINST (? IN BOOLEAN MODE))";

    Page<Post> findByUserId(Long userId, Pageable pageable);

    @Query(value = SELECT_UNBLOCKED_POST_QUERY, nativeQuery = true)
    Optional<Post> findUnblockedPost(Long id);

    @Query(value = SELECT_ALL_UNBLOCKED_POSTS_QUERY, nativeQuery = true)
    List<Post> findAllUnblockedPosts();

    @Query(value = SELECT_ALL_UNBLOCKED_POSTS_QUERY, nativeQuery = true)
    Page<Post> findAllUnblockedPosts(Pageable pageable);

    @Query(value = FIND_POSTS_BY_CATEGORY_QUERY, nativeQuery = true)
    Page<Post> findByCategory(Long categoryId, Pageable pageable);

    @Query(value = SEARCH_POSTS_QUERY, nativeQuery = true)
    Page<Post> searchPosts(String postContent, String userNameOrEmail, String firstNameOrLastName, Pageable pageable);
}
