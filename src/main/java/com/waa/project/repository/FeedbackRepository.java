package com.waa.project.repository;

import com.waa.project.entity.Feedback;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends ListCrudRepository<Feedback, Long> {
}
