package com.waa.project.repository;

import com.waa.project.entity.Discussion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    Page<Discussion> findAllByStudentId(Long id, Pageable pageable);

    Optional<Discussion> findByIdAndStudentId(long id, long studentId);

    void deleteByIdAndStudentId(long id, long studentId);

    Optional<Discussion> findAllByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(String title, String body);
}
