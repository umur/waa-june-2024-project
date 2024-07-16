package edu.university_connect.repository;

import edu.university_connect.domain.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("SELECT r FROM Resource r WHERE r.user.id = :id")
    Page<Resource> getUserResourcePage(Long id, Pageable pageable);

    Page<Resource> findAllByTitleContainingIgnoreCaseOrderByCreatedAtDesc(Pageable pageable,String title);

    Page<Resource> findAllOrderByCreatedAtDesc(Pageable pageable,String title);
}
