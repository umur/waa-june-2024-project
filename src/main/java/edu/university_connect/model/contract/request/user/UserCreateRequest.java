package edu.university_connect.model.contract.request.user;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String username;

    private String email;

    private String password;

    private boolean enabled;

}
