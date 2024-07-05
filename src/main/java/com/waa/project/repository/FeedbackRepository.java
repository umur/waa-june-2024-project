package com.waa.project.repository;

import com.waa.project.entity.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends ListCrudRepository<Feedback, Long> {
    List<Feedback> findFeedbackByCategoryId(Long id);

    List<Feedback> findFeedbackByCategoryName(String name);

    @Query("SELECT fc.name, COUNT(f.category) " +
           "FROM Feedback f " +
           "JOIN f.category fc " +
           "GROUP BY fc.name")
    List<Object[]> findFeedbackByCategoryCount();

}
