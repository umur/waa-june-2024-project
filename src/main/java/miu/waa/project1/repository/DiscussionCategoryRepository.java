package miu.waa.project1.repository;

import miu.waa.project1.model.DiscussionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionCategoryRepository extends JpaRepository<DiscussionCategory, Long> {
}
