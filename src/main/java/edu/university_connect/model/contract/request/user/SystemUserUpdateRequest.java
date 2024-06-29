package edu.university_connect.model.contract.request.user;

import lombok.Data;

@Data
public class SystemUserUpdateRequest {

    private String username;

    private String email;
    private boolean enabled;
}
