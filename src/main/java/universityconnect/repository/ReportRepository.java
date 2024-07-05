package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import universityconnect.domain.Report;
import universityconnect.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("select r.reportedUser from Report r where r.reporterUser.id = :id")
    List<User> findByReportedUserByReporterUserId(@Param("id") long id);

    @Query("select r.reporterUser from Report r where r.reportedUser.id = :id")
    List<User> findByReporterUserByReportedUserId(@Param("id") long id);

}
