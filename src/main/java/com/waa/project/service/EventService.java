package com.waa.project.service;

import com.waa.project.dto.EventDTO;

import java.util.List;

public interface EventService {
    public List<EventDTO> findAll();
    public EventDTO save(EventDTO eventDTO);
    public EventDTO findById(Long id);
}
