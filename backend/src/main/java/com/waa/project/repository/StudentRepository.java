package com.waa.project.repository;


import com.waa.project.entity.Event;
import com.waa.project.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT e FROM Student s JOIN s.eventList e WHERE s.id = :studentId")
    List<Event> findEventsByStudentId(@Param("studentId") Long studentId);

    Optional<Student> findByUsername(String username);
    
    @Query(
            "SELECT s FROM Student s WHERE s.username LIKE %:text% OR s.firstName LIKE %:text% OR s.lastName LIKE " +
            "%:text% OR s.email LIKE %:text%"
    )
    Page<Student> searchByText(String text, Pageable pageable);

    void deleteByUsername(String username);
}

