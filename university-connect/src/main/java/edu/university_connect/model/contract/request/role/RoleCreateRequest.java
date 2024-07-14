package edu.university_connect.model.contract.request.role;

import lombok.Data;

import java.util.Set;

@Data
public class RoleCreateRequest {


    private String name;

    private String code;

    private String description;

    private Set<String> actions;

}
