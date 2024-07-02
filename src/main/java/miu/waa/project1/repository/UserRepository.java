package miu.waa.project1.repository;

import miu.waa.project1.model.User;
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
            "AND (:major is null or u.major LIKE %:major%) " +
            "AND (:entryYear is null or u.entryYear = :entryYear) " +
            "AND (:keyword is null OR (i.name LIKE %:keyword% " +
            "OR a.description LIKE %:keyword% " +
            "OR e.name LIKE %:keyword%))" )
    List<User> findByMajorEntryYearAndRelevant(@Param("major") String major, @Param("entryYear") Integer entryYear, @Param("keyword") String keyword);
}
