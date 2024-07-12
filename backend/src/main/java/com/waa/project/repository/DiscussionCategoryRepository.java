package com.waa.project.repository;

import com.waa.project.entity.DiscussionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionCategoryRepository extends JpaRepository<DiscussionCategory, Long> {
}
