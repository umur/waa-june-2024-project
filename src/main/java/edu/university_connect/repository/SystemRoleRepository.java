package edu.university_connect.repository;

import edu.university_connect.model.entity.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
    Optional<SystemRole> findByCodeIgnoreCase(String roleCode);
}
