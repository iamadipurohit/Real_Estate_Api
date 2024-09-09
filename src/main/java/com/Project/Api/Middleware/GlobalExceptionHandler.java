package com.Project.Api.Middleware;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource; // Assuming you have a MessageSource bean configured

    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class,Exception.class, ConstraintViolationException.class, BindException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // Create a JSON response
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);

        String errorMessage;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex instanceof DataIntegrityViolationException) {
            errorMessage = messageSource.getMessage("error.username.unique", null, request.getLocale());
            status = HttpStatus.CONFLICT; // Conflict for uniqueness constraint violation
        } else if (ex instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            errorMessage = processFieldErrors(result);
        } else if (ex instanceof BindException) {
            BindingResult result = ((BindException) ex).getBindingResult();
            errorMessage = processFieldErrors(result);
        } else if (ex instanceof ConstraintViolationException) {
            errorMessage = ex.getMessage();
        } else {
            errorMessage = ex.getMessage();
        }

        response.put("message", errorMessage);
        return new ResponseEntity<>(response, status);
    }

    private String processFieldErrors(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        for (FieldError error : result.getFieldErrors()) {
            sb.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        }
        return sb.toString();
    }
}
