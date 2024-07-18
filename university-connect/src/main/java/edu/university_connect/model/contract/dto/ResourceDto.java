package edu.university_connect.model.contract.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResourceDto {
    private Long id;

    private String title;

    private String description;

    private String url;
    private List<String> files;

}
