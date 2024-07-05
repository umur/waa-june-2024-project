package edu.university_connect.repository;
import edu.university_connect.domain.entity.Resource;
import edu.university_connect.domain.entity.survey.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
    @Query("SELECT r FROM Survey r WHERE r.creator.id = :id")
    Page<Survey> getUserSurveyPage(Long id, Pageable pageable);

}
