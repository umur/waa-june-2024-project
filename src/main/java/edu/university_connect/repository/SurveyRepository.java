package edu.university_connect.repository;

import edu.university_connect.domain.entity.Action;
import edu.university_connect.domain.entity.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
}
