package com.employeemanagement.exception;

import com.employeemanagement.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    ErrorResponse errorResponse = new ErrorResponse();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        errorResponse.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> toNotFoundException(NotFoundException exception) {
        errorResponse.setMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> toConflictException(ConflictException exception) {
        errorResponse.setMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Object> toNoContentException(NoContentException exception) {
        errorResponse.setMessage(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(errorResponse);
    }
}
