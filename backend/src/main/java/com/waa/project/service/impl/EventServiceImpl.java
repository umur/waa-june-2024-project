package com.waa.project.service.impl;

import com.waa.project.contracts.StudentResponse;
import com.waa.project.dto.requests.EventRequestDto;
import com.waa.project.dto.responses.EventResponseDto;
import com.waa.project.dto.responses.EventsDto;
import com.waa.project.dto.responses.StudentEventResponseDTO;
import com.waa.project.entity.Event;
import com.waa.project.entity.Student;
import com.waa.project.exception.ResourceNotFoundException;
import com.waa.project.repository.EventRepository;
import com.waa.project.repository.StudentRepository;
import com.waa.project.service.EventService;
import com.waa.project.util.EventsErrorMessage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
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
    public Page<EventResponseDto> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable)
                              .map(event -> eventMapper.map(event, EventResponseDto.class));
    }

    @Override
    public  Page<EventResponseDto> searchByName(String name, Pageable pageable) {
        return eventRepository.searchByNameAndLocation(name, pageable)
                                .map(event -> eventMapper.map(event, EventResponseDto.class));
    }
    @Override
    public EventRequestDto save(EventRequestDto eventRequestDTO) {
        Event event = eventMapper.map(eventRequestDTO, Event.class);
        return eventMapper.map(eventRepository.save(event), EventRequestDto.class);
    }

    @Override
    public EventResponseDto findById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            return eventMapper.map(event, EventResponseDto.class);
        } else {
            throw new ResourceNotFoundException(EventsErrorMessage.eventNotFound(id));
        }
    }

    @Override
    public void deleteById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            eventRepository.delete(event);
        }else {
            throw new ResourceNotFoundException(EventsErrorMessage.eventNotFound(id));
        }
    }

    @Override
    public EventRequestDto update(EventRequestDto eventRequestDTO, Long id) {
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if (existingEvent != null) {
            eventMapper.map(eventRequestDTO, existingEvent);
            Event updatedEvent = eventRepository.save(existingEvent);
            return eventMapper.map(updatedEvent, EventRequestDto.class);
        } else {
            throw new ResourceNotFoundException(EventsErrorMessage.eventNotFound(id));
        }

    }

    @Override
    public EventRequestDto addEventReservation(Long eventId, Long studentId) {
        Event   event   = eventRepository.findById(eventId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (event != null && student != null) {
            event.getAttendedStudents().add(student);
            student.getEventList().add(event);
            eventRepository.save(event);
            studentRepository.save(student);
            return eventMapper.map(event, EventRequestDto.class);
        } else {
            throw new ResourceNotFoundException(EventsErrorMessage.eventNotFound(eventId));
        }
    }

    @Override
    public void removeEventReservation(Long eventId, Long studentId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (event != null && student != null) {
            event.getAttendedStudents().remove(student);
            student.getEventList().remove(event);
            eventRepository.save(event);
            studentRepository.save(student);
        } else {
            throw new ResourceNotFoundException(EventsErrorMessage.eventNotFound(eventId));
        }
    }
    @Override
    public List<EventsDto> getAttendeesForEvent(Long eventId) {
        return eventRepository.findStudentsByEventId(eventId).stream()
                              .map(event -> eventMapper.map(event, EventsDto.class))
                              .collect(Collectors.toList());
    }

    public List<StudentEventResponseDTO> getEventsByStudentId(Long studentId) {
        return studentRepository.findEventsByStudentId(studentId).stream()
                              .map(event -> eventMapper.map(event, StudentEventResponseDTO.class))
                              .collect(Collectors.toList());
    }

}
