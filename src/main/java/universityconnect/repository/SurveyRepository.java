package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Survey;

@Repository
public interface SurveyRepository  extends JpaRepository<Survey, Long> {
}
