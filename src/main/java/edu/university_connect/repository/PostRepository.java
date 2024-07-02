package edu.university_connect.repository;

import edu.university_connect.domain.entity.discussionthread.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
