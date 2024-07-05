package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universityconnect.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
