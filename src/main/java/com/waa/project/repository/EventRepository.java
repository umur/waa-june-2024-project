package com.waa.project.repository;

import com.waa.project.entity.Event;
import com.waa.project.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends ListCrudRepository<Event, Long> {
    @Query("SELECT e.attendedStudents FROM Event e WHERE e.id = :eventId")
    List<Student> findStudentsByEventId(@Param("eventId") Long eventId);
}
