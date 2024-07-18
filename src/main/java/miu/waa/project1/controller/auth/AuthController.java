package miu.waa.project1.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import miu.waa.project1.dto.auth.AuthResponseDTO;
import miu.waa.project1.dto.auth.LoginDTO;
import miu.waa.project1.dto.auth.SignUpDTO;
import miu.waa.project1.dto.auth.TokenDTO;
import miu.waa.project1.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private  final AuthServiceImpl authService;

    // Config in application.properties
    @Value("${block.attempts.login.minutes}")
    private int blockMinutes;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDTO request) {
        TokenDTO token = authService.register(request);
        if (token == null) return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginDTO request) {
        AuthResponseDTO authResponse = authService.signIn(request);
        if (authResponse == null) return ResponseEntity.badRequest().body("Email is not registered!");
        if (authResponse.getLoginAttempts() > 0) {
            if (authResponse.getIsBlocked()) {
                return ResponseEntity.badRequest().body("Account is locked. Please try again after " + blockMinutes + " minutes.");
            } else {
                return ResponseEntity.badRequest().body("Email or password is incorrect!");
            }
        }
        return ResponseEntity.ok(authResponse.getTokenDTO());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            authService.refreshToken(request, response);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error refreshing token");
        }
    }
}
