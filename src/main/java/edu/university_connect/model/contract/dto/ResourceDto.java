package edu.university_connect.model.contract.dto;

import lombok.Data;

@Data
public class ResourceDto {
    private Long id;

    private String title;

    private String description;

    private String url;
}
