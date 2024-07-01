package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Discussion;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
}