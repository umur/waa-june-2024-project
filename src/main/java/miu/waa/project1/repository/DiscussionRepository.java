package miu.waa.project1.repository;

import miu.waa.project1.model.Discussion;
import miu.waa.project1.model.DiscussionCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    @Query("SELECT d FROM Discussion d WHERE d.content LIKE %:keyword%")
    Page<Discussion> findAllByKeyword(@Param("keyword") Optional<String> keyword, Pageable pageable);
}
