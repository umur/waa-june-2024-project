package miu.waa.project1.repository;

import miu.waa.project1.model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<Logger, Long> {
}
