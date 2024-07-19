package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.SurveyStudent;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyStudentRepository extends JpaRepository<SurveyStudent, Long> {

    List<SurveyStudent> findByStudentId(Long studentId);

    Optional<SurveyStudent> findBySurveyIdAndStudentId(Long surveyId, Long studentId);

    List<SurveyStudent> findBySurveyId(Long surveyId);
}
