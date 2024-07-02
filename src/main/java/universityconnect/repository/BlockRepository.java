package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Block;
import universityconnect.domain.User;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
    @Query("select b.blockedUser from Block b where b.blockerUser.id = :id")
    List<User> findBlockedUsersByBlockerUserId(@Param("id") long id);

    @Query("select b.blockerUser from Block b where b.blockedUser.id = :id")
    List<User> findBlockerUsersByBlockedUserId(@Param("id") long id);
}
