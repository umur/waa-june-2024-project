package miu.waa.project1.repository;

import miu.waa.project1.model.Discussion;
import miu.waa.project1.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
