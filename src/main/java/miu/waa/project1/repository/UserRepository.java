package miu.waa.project1.repository;

import miu.waa.project1.common.Role;
import miu.waa.project1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    List<User> findByRole(Role role);
}
