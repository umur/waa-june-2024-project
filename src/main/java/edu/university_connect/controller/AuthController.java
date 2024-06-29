package edu.university_connect.controller;

import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.contract.request.auth.AuthenticationRequest;
import edu.university_connect.model.contract.request.auth.RefreshTokenRequest;
import edu.university_connect.model.contract.response.common.ApiResponse;
import edu.university_connect.model.contract.response.common.AuthenticationResponse;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.impl.AuthServiceImpl;
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
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthServiceImpl service;

    private final MessagingService messagingService;


    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest data) {


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
}
