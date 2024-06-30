package edu.miu.cs545.project.repository;

import edu.miu.cs545.project.model.entity.ThreadPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreadPostRepository extends JpaRepository<ThreadPost,Long>{
  Optional<ThreadPost> findByTitle(String threadTitle);
}