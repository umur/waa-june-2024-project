package edu.university_connect.repository;

import edu.university_connect.domain.entity.ModerationReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ModerationReport, Long> {
}
