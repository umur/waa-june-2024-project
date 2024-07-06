package miu.waa.project1.repository;

import miu.waa.project1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    User findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u " +
            "LEFT JOIN u.interests i " +
            "LEFT JOIN u.achievements a " +
            "LEFT JOIN u.activities e " +
            "WHERE u.role = 'STUDENT' " +
            "AND (:major IS NULL OR u.major LIKE %:major%) " +
            "AND (:entryYear IS NULL OR u.entryYear = :entryYear) " +
            "AND (:keyword IS NULL OR (u.firstName LIKE %:keyword% " +
            "OR u.lastName LIKE %:keyword% " +
            "OR i.name LIKE %:keyword% " +
            "OR a.description LIKE %:keyword% " +
            "OR e.name LIKE %:keyword%))")
    Page<User> findByMajorEntryYearAndRelevant(@Param("major") String major, @Param("entryYear") Integer entryYear, @Param("keyword") String keyword, Pageable pageable);
}
