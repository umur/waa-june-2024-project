package com.waa.project.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDto {
    @NotNull(message = "Name cannot be null.")
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 30, message = "Name must be between 3 and 30 characters long.")
    private String    name;
    @NotNull(message = "Description cannot be null.")
    @NotBlank(message = "Description cannot be blank.")
    private String    description;
    @NotNull(message = "Event Date cannot be null.")
    @NotBlank(message = "Event Date  cannot be blank.")
    private LocalDate eventDate;
    @NotNull(message = "Event Time cannot be null.")
    @NotBlank(message = "Event Time  cannot be blank.")
    private LocalTime eventTime;
}
