package edu.miu.cs545.project.repository;

import edu.miu.cs545.project.model.entity.Post;
import edu.miu.cs545.project.model.entity.ThreadPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findPostByThreadPost(ThreadPost threadPost, Pageable pageable);
}