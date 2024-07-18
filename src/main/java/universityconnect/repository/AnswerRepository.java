package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
