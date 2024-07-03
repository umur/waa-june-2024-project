package edu.university_connect.repository;

import edu.university_connect.domain.entity.survey.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyResponseRepository extends JpaRepository<SurveyQuestion,Long> {
}
