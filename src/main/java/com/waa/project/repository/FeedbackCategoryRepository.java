package com.waa.project.repository;

import com.waa.project.entity.FeedbackCategory;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackCategoryRepository extends ListCrudRepository<FeedbackCategory, Long> {
}
