package com.wy.synthetic.exception;

import com.wy.synthetic.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(400, ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), Instant.now());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(CommandQueueFullException.class)
    public ResponseEntity<ErrorResponse> handleQueueFull(CommandQueueFullException ex) {
        ErrorResponse err = new ErrorResponse(503, ex.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse err = new ErrorResponse(500, "Internal server error", Instant.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
