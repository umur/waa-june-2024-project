package edu.university_connect.repository;

import edu.university_connect.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username ")
    List<User> findAllByUsername(@Param("username") String username);

    @Query("SELECT u.blockedUsers FROM User u WHERE u.id = :userId")
    Page<User> findBlockedUsers(@Param("userId") Long userId, Pageable pageable);
}
