package com.waa.project.security.aop;

import com.waa.project.security.contract.ErrorResponse;
import com.waa.project.security.exception.ActionForbiddenException;
import com.waa.project.security.exception.ExpiredTokenException;
import com.waa.project.security.util.AuthErrorMessages;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthExceptionHandler {

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> handleException(ExpiredTokenException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleException(BadCredentialsException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UsernameNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(AuthErrorMessages.invalidToken());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ActionForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleException(ActionForbiddenException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
