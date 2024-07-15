package com.waa.project.service;

import com.waa.project.security.contract.AuthUserResponse;

public interface UserService {
    AuthUserResponse findByUsername(String username);
}
