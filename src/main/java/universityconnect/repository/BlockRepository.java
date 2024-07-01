package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
}
