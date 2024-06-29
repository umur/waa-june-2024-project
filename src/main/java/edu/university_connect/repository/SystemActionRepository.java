package edu.university_connect.repository;

import edu.university_connect.model.entity.SystemAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemActionRepository extends JpaRepository<SystemAction, Long> {
    Optional<SystemAction> findByCodeIgnoreCase(String code);
}
