package edu.university_connect.repository;

import edu.university_connect.domain.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long> {
    Optional<Action> findByCodeIgnoreCase(String code);
}
