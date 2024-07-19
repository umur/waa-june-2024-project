package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universityconnect.domain.DiscussionCategory;

public interface DiscussionCategoryRepository extends JpaRepository<DiscussionCategory,Long> {
}
