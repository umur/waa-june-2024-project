package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universityconnect.domain.EventAttendance;

public interface EventAttendanceRepository extends JpaRepository<EventAttendance,Long> {
}
