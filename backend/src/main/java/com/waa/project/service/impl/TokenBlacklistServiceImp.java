package com.waa.project.service.impl;

import com.waa.project.service.TokenBlacklistService;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistServiceImp implements TokenBlacklistService {

    @Override
    public void addTokenToBlacklist(String token) {

    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return false;
    }
}
