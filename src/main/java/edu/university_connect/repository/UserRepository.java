package edu.university_connect.repository;

import edu.university_connect.domain.entity.Resource;
import edu.university_connect.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username ")
    List<User> findAllByUsername(@Param("username") String username);
}
