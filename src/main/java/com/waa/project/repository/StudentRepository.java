package com.waa.project.repository;


import com.waa.project.dto.requests.EventDTO;
import com.waa.project.entity.Event;
import com.waa.project.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {
    @Query("SELECT e FROM Student s JOIN s.eventList e WHERE s.id = :studentId")
    List<EventDTO> findEventsByStudentId(@Param("studentId") Long studentId);
}
