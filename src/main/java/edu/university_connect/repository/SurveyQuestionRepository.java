package edu.university_connect.repository;

import edu.university_connect.domain.entity.survey.Survey;
import edu.university_connect.domain.entity.survey.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion,Long> {
}
