package edu.university_connect.model.contract.request.action;

import lombok.Data;

@Data
public class ActionUpdateRequest {

    private String name;

    private String code;

    private String description;
}
