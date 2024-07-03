package edu.university_connect.repository;

import edu.university_connect.domain.entity.survey.SurveyQuestion;
import edu.university_connect.domain.entity.survey.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse,Long> {
}
