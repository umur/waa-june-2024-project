package com.waa.project.security.contract;

import com.waa.project.enums.RoleType;
import lombok.Data;

@Data
public class AuthUserResponse {
    private Long     id;
    private String   username;
    private String   password;
    private RoleType roleType;
}
