package com.waa.project.repository;

import com.waa.project.entity.Event;
import com.waa.project.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e.attendedStudents FROM Event e WHERE e.id = :eventId")
    List<Student> findStudentsByEventId(@Param("eventId") Long eventId);

    List<Event> findByNameContaining(String name);
    @Query("SELECT e FROM Event e WHERE e.name LIKE %:name% OR e.location LIKE %:name%")
    Page<Event> searchByNameAndLocation(String name, Pageable pageable);
}
