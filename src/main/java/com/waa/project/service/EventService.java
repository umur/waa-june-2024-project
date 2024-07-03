package com.waa.project.service;

import com.waa.project.dto.requests.EventRequestDto;
import com.waa.project.dto.responses.EventResponseDto;
import com.waa.project.dto.responses.EventsDto;
import com.waa.project.dto.responses.StudentEventResponseDTO;

import java.util.List;

public interface EventService {
    public List<EventResponseDto> findAll();
    public EventRequestDto save(EventRequestDto eventRequestDTO);
    public EventResponseDto findById(Long id);
    public void deleteById(Long id);
    public EventRequestDto update(EventRequestDto eventRequestDTO, Long id);

    public EventRequestDto addEventReservation(Long eventId, Long studentId);
    public String removeEventReservation(Long eventId, Long studentId);

    public List<EventsDto> getAttendeesForEvent(Long eventId);
    public List<StudentEventResponseDTO> getEventsByStudentId(Long studentId);
}
