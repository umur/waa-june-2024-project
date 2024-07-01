package com.waa.project.mapper;

import com.waa.project.dto.EventDTO;
import com.waa.project.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setDateTime(event.getDateTime());
        return dto;
    }

    public Event toEntity(EventDTO dto) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDateTime(dto.getDateTime());
        return event;
    }
}
