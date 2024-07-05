package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
