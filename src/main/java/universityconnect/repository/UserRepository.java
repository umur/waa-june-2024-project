package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universityconnect.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
