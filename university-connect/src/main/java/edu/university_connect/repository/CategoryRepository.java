package edu.university_connect.repository;
import edu.university_connect.domain.entity.discussionthread.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long> {
    Optional<Category> findByTitleIgnoreCase(String title);
}
