package miu.waa.project1.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import miu.waa.project1.dto.auth.AuthResponseDTO;
import miu.waa.project1.dto.auth.LoginDTO;
import miu.waa.project1.dto.auth.SignUpDTO;
import miu.waa.project1.dto.auth.TokenDTO;

import java.io.IOException;

public interface AuthService {
    TokenDTO register(SignUpDTO request);
    AuthResponseDTO signIn(LoginDTO request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
