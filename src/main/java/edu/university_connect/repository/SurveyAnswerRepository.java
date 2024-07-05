package edu.university_connect.repository;
import edu.university_connect.domain.entity.survey.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyAnswerRepository extends JpaRepository<SurveyResponse,Long> {
}
