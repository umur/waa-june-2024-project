package com.waa.project.service.impl;

import com.waa.project.dto.requests.EventDTO;
import com.waa.project.entity.Event;
import com.waa.project.entity.Student;
import com.waa.project.repository.EventRepository;
import com.waa.project.repository.StudentRepository;
import com.waa.project.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper       eventMapper;
    private final StudentRepository studentRepository;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper eventMapper,
                            StudentRepository studentRepository
                           ) {
        this.eventRepository = eventRepository;
        this.eventMapper     = eventMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream()
                              .map(event -> eventMapper.map(event, EventDTO.class))
                              .collect(Collectors.toList());
    }

    @Override
    public EventDTO save(EventDTO eventDTO) {
        Event event = eventMapper.map(eventDTO, Event.class);
        return eventMapper.map(eventRepository.save(event), EventDTO.class);
    }

    @Override
    public EventDTO findById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            return eventMapper.map(event, EventDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            eventRepository.delete(event);
        }else {
            return;
        }
    }

    @Override
    public EventDTO update(EventDTO eventDTO, Long id) {
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if (existingEvent != null) {
            eventMapper.map(eventDTO, existingEvent);
            Event updatedEvent = eventRepository.save(existingEvent);
            return eventMapper.map(updatedEvent, EventDTO.class);
        } else {
            return null;
        }

    }

    @Override
    public EventDTO addEventReservation(Long eventId, Long studentId) {
        Event   event   = eventRepository.findById(eventId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (event != null && student != null) {
            event.getAttendedStudents().add(student);
            student.getEventList().add(event);
            eventRepository.save(event);
            studentRepository.save(student);
            return eventMapper.map(event, EventDTO.class);
        }

        return null;
    }

    @Override
    public String removeEventReservation(Long eventId, Long studentId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (event != null && student != null) {
            event.getAttendedStudents().remove(student);
            student.getEventList().remove(event);
            eventRepository.save(event);
            studentRepository.save(student);
            return "Reservation removed.";
        }
        return "Student or Event not found.";

    }
    @Override
    public List<Student> getAttendeesForEvent(Long eventId) {
        return eventRepository.findStudentsByEventId(eventId);
    }

    public List<EventDTO> getEventsByStudentId(Long studentId) {
        return studentRepository.findEventsByStudentId(studentId);
    }
}
