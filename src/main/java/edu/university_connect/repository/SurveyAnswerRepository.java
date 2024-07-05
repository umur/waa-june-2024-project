package edu.university_connect.repository;
import edu.university_connect.domain.entity.survey.SurveyQuestion;
import edu.university_connect.domain.entity.survey.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyAnswerRepository extends JpaRepository<SurveyResponse,Long> {

    @Query("SELECT sr FROM SurveyResponse sr where sr.surveyQuestion.id=:surveyQuestionId")
    List<SurveyResponse> getSurveyAnswersBySurveyQuestionId(Long surveyQuestionId);
}
