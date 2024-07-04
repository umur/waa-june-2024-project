package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.auth.SignUpRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.contract.request.auth.AuthenticationRequest;
import edu.university_connect.model.contract.request.auth.RefreshTokenRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.contract.response.AuthenticationResponse;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.auth.AuthServiceImpl;
import edu.university_connect.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthServiceImpl service;
    private final UserService userService;

    private final MessagingService messagingService;


    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> signIn(@Valid @RequestBody AuthenticationRequest data) {


        AuthenticationResponse response = service.authenticate(new UsernamePasswordAuthenticationToken
                    (data.getUsername(), data.getPassword()));
        ApiResponse<AuthenticationResponse> apiResponse =  new ApiResponse<AuthenticationResponse>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20007));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/sign-in/refresh-token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> signInFromRefreshToken(@Valid @RequestBody RefreshTokenRequest data) {


        AuthenticationResponse response = service.authenticateRefreshToken(data);
        ApiResponse<AuthenticationResponse> apiResponse =  new ApiResponse<AuthenticationResponse>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20007));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<UserDto>> signUp(@Valid @RequestBody SignUpRequest data) {
        UserDto response=userService.signUp(data);
        ApiResponse<UserDto> apiResponse =  new ApiResponse<UserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20000));
        return ResponseEntity.ok(apiResponse);
    }
}
