package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.SurveyStudent;

import java.util.List;

@Repository
public interface SurveyStudentRepository extends JpaRepository<SurveyStudent, Long> {

    List<SurveyStudent> findByStudentId(Long studentId);
}
