package miu.waa.project1.repository;

import miu.waa.project1.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Survey findAllByUserId(Long userId);
}
