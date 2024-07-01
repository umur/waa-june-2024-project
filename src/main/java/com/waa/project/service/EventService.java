package com.waa.project.service;

import com.waa.project.dto.requests.EventDTO;

import java.util.List;

public interface EventService {
    public List<EventDTO> findAll();
    public EventDTO save(EventDTO eventDTO);
    public EventDTO findById(Long id);
    public void deleteById(Long id);
    public EventDTO update(EventDTO eventDTO,  Long id);

    public EventDTO addEventAttendee(EventDTO eventDTO);
    public void removeEventAttendee(EventDTO eventDTO);
}
