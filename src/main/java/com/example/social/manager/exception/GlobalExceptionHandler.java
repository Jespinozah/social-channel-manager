package com.example.social.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<SocialManagerErrorResponse> ResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new SocialManagerErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Object> ResourceNotFoundException(UnauthorizedException exception) {
        return new ResponseEntity<>(new SocialManagerErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return new ResponseEntity<>(new SocialManagerErrorResponse(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
