package edu.university_connect.model.contract.request.action;

import lombok.Data;

@Data
public class SystemActionUpdateRequest {

    private String name;

    private String code;

    private String description;
}
