package com.waa.project.service;

import com.waa.project.dto.requests.EventDTO;
import com.waa.project.entity.Event;
import com.waa.project.entity.Student;

import java.util.List;

public interface EventService {
    public List<EventDTO> findAll();
    public EventDTO save(EventDTO eventDTO);
    public EventDTO findById(Long id);
    public void deleteById(Long id);
    public EventDTO update(EventDTO eventDTO,  Long id);

    public EventDTO addEventReservation(Long eventId, Long studentId);
    public String removeEventReservation(Long eventId, Long studentId);

    public List<Student> getAttendeesForEvent(Long eventId);
    public List<EventDTO> getEventsByStudentId(Long studentId);
}
