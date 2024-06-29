package edu.university_connect.model.contract.request.user;

import lombok.Data;

@Data
public class SystemUserCreateRequest {

    private String username;

    private String email;

    private String password;

    private boolean enabled;

}
