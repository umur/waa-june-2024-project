package universityconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import universityconnect.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUserId(long userId);


}
