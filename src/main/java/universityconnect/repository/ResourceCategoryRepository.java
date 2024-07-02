package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.ResourceCategory;

@Repository
public interface ResourceCategoryRepository extends JpaRepository<ResourceCategory,Long> {
}
