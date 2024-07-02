package com.waa.project.security.contract;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
