package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universityconnect.domain.DiscussionThread;

public interface DiscussionThreadRepository extends JpaRepository<DiscussionThread,Long> {
}
