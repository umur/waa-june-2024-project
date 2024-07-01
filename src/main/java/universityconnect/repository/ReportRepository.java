package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
