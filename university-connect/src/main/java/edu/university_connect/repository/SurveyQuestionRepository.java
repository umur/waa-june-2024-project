package edu.university_connect.repository;
import edu.university_connect.domain.entity.survey.Survey;
import edu.university_connect.domain.entity.survey.SurveyQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion,Long> {

    @Query("SELECT sq FROM SurveyQuestion sq where sq.survey.id=:surveyId")
    List<SurveyQuestion> getSurveyQuestionsBySurveyId(Long surveyId);
}
