package edu.university_connect.model.contract.request.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRequest {
    private String name;
    private String description;
    private String location;
    private LocalDate date;
}
