package org.sopt.global.exception;

import org.sopt.global.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> customException(Exception e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getErrorResponse());
    }
}